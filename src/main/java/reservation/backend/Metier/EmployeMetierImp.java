package reservation.backend.Metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reservation.backend.Entity.Employe;
import reservation.backend.Entity.EmployeRepresentation;
import reservation.backend.Entity.EmployeViewModel;
import reservation.backend.Entity.InfosToUpdateSuperviseur;
import reservation.backend.Repository.EmployeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EmployeMetierImp implements EmployeMetier {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public EmployeViewModel findEmployeViewModelById(String id) {
        Optional<Employe> employe=employeRepository.findById(id);
        if(employe.isPresent()) {
            return convertToEmployeViewModel(employe.get());
        }
        else
            return null;
    }

    public Employe findEmployeById(String id) {
        Optional<Employe> employe=employeRepository.findById(id);
        if(employe.isPresent()) {
            return employe.get();
        }
        else
            return null;
    }

    @Override
    public EmployeViewModel findEmployeByNom(String nom) {
        Optional<Employe> employe=employeRepository.findEmployeByNom(nom);
        if(employe.isPresent()) {
            return convertToEmployeViewModel(employe.get());
        }
        else
            return null;
    }

    @Override
    public EmployeViewModel saveEmploye(EmployeRepresentation employeRepresentation) {
        Employe employePersist = new Employe();
        employePersist.setEmail(employeRepresentation.getEmail());
        employePersist.setLogin(employeRepresentation.getLogin());
        employePersist.setMotDePasse(generatePassword());
        employePersist.setNom(employeRepresentation.getNom());
        employePersist.setPrenom(employeRepresentation.getPrenom());
        employePersist.setPhone(employeRepresentation.getPhone());
        employePersist.setSuperviseur(null);
        return convertToEmployeViewModel(employeRepository.save(employePersist));
    }

    @Override
    public List<EmployeViewModel> findAllEmploye() {
        List<Employe> employes = employeRepository.findAll();

        List<EmployeViewModel> employeViewModels = employes.stream()
                .map(employe -> this.convertToEmployeViewModel(employe))
                .collect(Collectors.toList());
        return employeViewModels;
    }

    @Override
    public List<Employe> findAllBySuperviseur(String idsuperviseur) {
        return employeRepository.findAllBySuperviseur_Id(idsuperviseur);
    }

    public void deleteEmployeById(String id) throws ResourceNotFoundException {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : " + id));

        List<Employe> employeList = findAllBySuperviseur(id);
        employeList.forEach(e ->{
            e.setSuperviseur(null);
            employeRepository.save(e);
        });
        employeRepository.deleteEmployeById(id);
    }

    @Override
    public Employe updateEmploye(String id, Employe employe) {
        Employe employeToUpdate = employeRepository.findById(id).get();
        if(employeToUpdate != null) {
            employeToUpdate.setNom(employe.getNom());
            employeToUpdate.setPrenom(employe.getPrenom());
            employeToUpdate.setPhone(employe.getPhone());
            employeToUpdate.setEmail(employe.getEmail());
            employeToUpdate.setLogin(employe.getLogin());
            employeToUpdate.setMotDePasse(employe.getMotDePasse());
            return employeRepository.save(employeToUpdate);
        }
        return null;
    }

    @Override
    public ResponseEntity<EmployeViewModel> updateSuperviseur(InfosToUpdateSuperviseur infosToUpdateSuperviseur) throws ResourceNotFoundException{
        Employe employe = employeRepository.findById(infosToUpdateSuperviseur.getIdemploye())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + infosToUpdateSuperviseur.getIdemploye()));
        if(infosToUpdateSuperviseur.getIdsuperviseur().compareTo("setnull")==0){
            employe.setSuperviseur(null);
        }
        else{
            Optional<Employe> superviseur = employeRepository.findById(infosToUpdateSuperviseur.getIdsuperviseur());
            if(superviseur.isPresent())
                employe.setSuperviseur(superviseur.get());
        }

        Employe updatedEmploye = employeRepository.save(employe);
        return ResponseEntity.ok(convertToEmployeViewModel(updatedEmploye));
    }

    public String generatePassword(){

        Random rand = new Random();
        String str="";
        for(int i = 0 ; i < 6 ; i++){
            char c = (char)(rand.nextInt(26) + 97);
            str += c;
        }
        return str;
    }

    public EmployeViewModel convertToEmployeViewModel(Employe employe){
        EmployeViewModel employeViewModel = new EmployeViewModel();
        employeViewModel.setId(employe.getId());
        employeViewModel.setEmail(employe.getEmail());
        employeViewModel.setLogin(employe.getLogin());
        employeViewModel.setNom(employe.getNom());
        employeViewModel.setPrenom(employe.getPrenom());
        employeViewModel.setPhone(employe.getPhone());
        if(employe.getSuperviseur() != null)
            employeViewModel.setSuperviseur(convertToEmployeViewModel(employe.getSuperviseur()));
        else
            employeViewModel.setSuperviseur(null);

        return employeViewModel;
    }


}
