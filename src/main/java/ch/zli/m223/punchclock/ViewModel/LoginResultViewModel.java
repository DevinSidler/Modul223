package ch.zli.m223.punchclock.ViewModel;

public class LoginResultViewModel {

    private String token;

    public LoginResultViewModel(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
