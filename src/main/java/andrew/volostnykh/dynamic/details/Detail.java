package andrew.volostnykh.dynamic.details;

public interface Detail<T> {

    Long getId();
    T getValue();
     String getName();
     String getCode();
     DetailType getType();

}
