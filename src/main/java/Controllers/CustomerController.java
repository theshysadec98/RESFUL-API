package Controllers;

import Models.Customer;
import Services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    ICustomerService iCustomerService;

    @GetMapping
    public ResponseEntity<Iterable<Customer>> findAllCustomer(){
        List<Customer> list = (List<Customer>) iCustomerService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable long id){
        Optional<Customer> customerOptional = iCustomerService.findById(id);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
     }

     @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(iCustomerService.save(customer), HttpStatus.CREATED);
     }

     @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer){
        Optional<Customer> customerOptional = iCustomerService.findById(id);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        return new ResponseEntity<>(iCustomerService.save(customer), HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable long id){
        Optional<Customer> customerOptional = iCustomerService.findById(id);

        if(!customerOptional.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         iCustomerService.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
     }
}
