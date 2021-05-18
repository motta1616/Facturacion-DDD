package co.com.aquiles.facturacion.infra.bus;

public interface EventSubscriber {
    void subscribe(String eventType, String exchange);
}
