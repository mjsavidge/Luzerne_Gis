package loginapp;

public enum option {
    Admin , Worker;

    option(){    }

    public String value(){
        return name();
    }

    public static option fromValue(String v){
        return valueOf(v);
    }
}
