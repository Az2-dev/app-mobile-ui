package ma.emi.store.model.dto;

public class FournisseurDto {

    private Long id;
    public String name;
    public String mail;
    public String numero;

    public FournisseurDto() {
        // Initiate nothing !
    }

    public FournisseurDto(Long id, String name, String mail, String numero) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.numero = numero;
    }

    public FournisseurDto(String name, String mail, String numero) {
        this.name = name;
        this.mail = mail;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
