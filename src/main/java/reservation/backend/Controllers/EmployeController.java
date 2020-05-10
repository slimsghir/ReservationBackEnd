package reservation.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reservation.backend.Entity.Employe;
import reservation.backend.Entity.EmployeRepresentation;
import reservation.backend.Entity.EmployeViewModel;
import reservation.backend.Entity.InfosToUpdateSuperviseur;
import reservation.backend.Metier.EmployeMetier;
import reservation.backend.Metier.ResourceNotFoundException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/employe")
public class EmployeController {

    @Autowired
    EmployeMetier employeMetierImp;


    @GetMapping("/findall")
    public List<EmployeViewModel> findAllEmploye(){
        return employeMetierImp.findAllEmploye();
    }

    @GetMapping("/find/{id}")
    public EmployeViewModel findById(@PathVariable String id){
        return employeMetierImp.findEmployeViewModelById(id);
    }

    @PostMapping("/save")
    public EmployeViewModel saveEmploye(@RequestBody EmployeRepresentation employeRepresentation){
        return employeMetierImp.saveEmploye(employeRepresentation);
    }

    @PostMapping("/updateSuperviseur")
    public ResponseEntity<EmployeViewModel> updateSuperviseur(@RequestBody InfosToUpdateSuperviseur infosToUpdateSuperviseur) throws ResourceNotFoundException {
        return employeMetierImp.updateSuperviseur(infosToUpdateSuperviseur);
    }


    @PutMapping("/update/{id}")
    public Employe updateEmploye(@PathVariable String id, @RequestBody Employe employe) {
        return employeMetierImp.updateEmploye(id,employe);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) throws ResourceNotFoundException {
        employeMetierImp.deleteEmployeById(id);
    }
}
