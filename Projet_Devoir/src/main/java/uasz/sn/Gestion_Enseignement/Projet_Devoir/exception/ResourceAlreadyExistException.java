package uasz.sn.Gestion_Enseignement.Projet_Devoir.exception;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(String message){
        super(message);
    }
}
