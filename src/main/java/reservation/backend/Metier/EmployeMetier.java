package reservation.backend.Metier;

import org.springframework.http.ResponseEntity;
import reservation.backend.Entity.Employe;
import reservation.backend.Entity.EmployeRepresentation;
import reservation.backend.Entity.EmployeViewModel;
import reservation.backend.Entity.InfosToUpdateSuperviseur;

import java.util.List;
import java.util.Optional;

public interface EmployeMetier {
    public Employe findEmployeById(String id);
    public EmployeViewModel findEmployeViewModelById(String id);
    public EmployeViewModel findEmployeByNom(String nom);
    public EmployeViewModel saveEmploye(EmployeRepresentation employeRepresentation);
    public List<EmployeViewModel> findAllEmploye();
    public void deleteEmployeById(String id) throws ResourceNotFoundException;
    public Employe updateEmploye(String id, Employe employe);
    public ResponseEntity<EmployeViewModel> updateSuperviseur(InfosToUpdateSuperviseur infosToUpdateSuperviseur) throws ResourceNotFoundException;
    public List<Employe> findAllBySuperviseur(String idsuperviseur);
}
