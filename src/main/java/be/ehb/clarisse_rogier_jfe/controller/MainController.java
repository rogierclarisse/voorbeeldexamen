package be.ehb.clarisse_rogier_jfe.controller;

import be.ehb.clarisse_rogier_jfe.dao.BidDao;
import be.ehb.clarisse_rogier_jfe.dao.PersonDao;
import be.ehb.clarisse_rogier_jfe.dao.ProductDao;
import be.ehb.clarisse_rogier_jfe.entities.Bid;
import be.ehb.clarisse_rogier_jfe.entities.Person;
import be.ehb.clarisse_rogier_jfe.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class MainController {
    private BidDao bidDao;
    private PersonDao personDao;
    private ProductDao productDao;

    @Autowired
    public MainController(BidDao bidDao, PersonDao personDao, ProductDao productDao) {
        this.bidDao = bidDao;
        this.personDao = personDao;
        this.productDao = productDao;
    }

    @GetMapping("/products")
    @ResponseBody
    public Iterable<Product> getAllProducts() {
        return productDao.findAll();
    }

    @PostMapping("/users/new")
    @ResponseBody
    public HttpStatus createUser(@RequestParam("name")String name,
                                 @RequestParam("email")String email){
        Person temp = new Person();
        temp.setName(name);
        temp.setEmail(email);

        personDao.save(temp);
        return HttpStatus.OK;
    }

    @PostMapping("/products/new")
    @ResponseBody
    public HttpStatus createProduct(@RequestParam("productname")String name,
                                    @RequestParam("startingPrice")Double price,
                                    @RequestParam("owner")int id,
                                    @RequestParam("endTime")String endtime){
        if(personDao.existsById(id)){
            Person owner = personDao.findById(id).get();
            LocalDateTime end_time = LocalDateTime.parse(endtime);

            Product product = new Product();
            product.setProductname(name);
            product.setStartingPrice(price);
            product.setEndTime(end_time);
            productDao.save(product);

            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Iterable<Product> getProductsById(
            @RequestParam("id")int id){
        if(personDao.existsById(id)){
            Person owner = personDao.findById(id).get();

            return owner.getAssortment();
        }
        return new ArrayList<Product>();
    }

    @PostMapping
    @ResponseBody
    public HttpStatus createBid(@RequestParam("productId")int product_id,
                                @RequestParam("personId")int person_id,
                                @RequestParam("price")double price){
        if(personDao.existsById(person_id)){
            Person bidder = personDao.findById(person_id).get();
            if(productDao.existsById(product_id)){
                Product product = productDao.findById(product_id).get();

                ArrayList<Bid> bids = new ArrayList<>();
                bidDao.findAllByProductId(product).forEach(bids::add);
                Bid lastBid = new Bid();

                if(!bids.isEmpty())
                    lastBid = bids.get(bids.size()-1);

                Bid bid = new Bid();
                bid.setPerson(bidder);
                bid.setProduct(product);
                bid.setPrice(price);

                if(bids.isEmpty() && price > product.getStartingPrice() || price > lastBid.getPrice()){
                    bidDao.save(bid);
                    return HttpStatus.ACCEPTED;
                }
            }
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }


}


