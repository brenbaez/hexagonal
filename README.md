# Hexagonal

## Ayuda de memoria
- El sistema puede ser usado por != actores, los cuales estarian interesados en interactuar con distintos subsets de informacion/funcionalidad. (para la administracion seria importante saber cuales son los productos mas comprados, productos y sus cantidades, los que actualizan el stock, los que cambian los precios, los miercoles a las 14 hs se actualizan los precios).
- Modelamos modelo de stock sin deficit

# Estructura
- Tenemos 2 modulos: Shop y Backoffice.

Reglas de negocio:
- Al añadir un producto al carrito, si este ultimo no existe aun, lo crea antes de añadir el producto. 
- Al momento de confirmar la compra
  - Se debe verificar la disponibilidad de cada producto.
  - Se crea Orden de Compra con fecha de creacion y estado.
- Los precios de una orden de compra son congelados por 24hs. Luego de ese tiempo, el sistema la cancela.
- Al cancelar una orden, la misma queda en estado "cancelled"
- Al pagar una orden, la misma queda en estado "paid"

# Modelos (dominio)

# Carrito de compras teraGamer
- Mail del user en Carrito de compras.

## Producto
- Tags de tipos(monitor/gamer) --> Mecanismo de filtro

## Descuentos
- Podemos tener tipos de descuentos cantidad fija, porcentaje, etc.

# Shop
## Use cases (primary)
- Search (specification): Filtrar favoritos de los usuarios (mas comprados), y por tags.
- Añadir al carrito (carrito id, prod_id)
- Borrar del carrito (carrito id)
- Confirmar la compra (carrito id, cod desc, address, mail) (antes de pagar)
- Cancelar orden (orden id) 
  - Actores: por el usuario, por ttl, manualmente por backoffice (hack, tarjeta de credito robado).
- Pagar

- Subscribirse a aparicion de stock de x productos. Cuando llegue el producto que quiero, notificame (sms/kesap/gmail)

## Use cases (secondary)
- Decrementar stock. (1)
- Incrementar el stock. (2)
- Notificar usuarios suscriptos acerca de disponibilidad del producto para el cual se suscribieron. (3)
- Notificar usuario (orden cancelada). (4)
- Notificar usuario (pago recibido). (5)
- Trigger product shipment (envio). (6)

# Backoffice / Retail:
- Crear productos para catalogo. Validaciones: unico codigo de barras. nombre unico. precio positivo. }
- Modificar stock.
- Actualizar precio. precio positivo.
- Cuales son los productos mas comprados(para temas de marketing, analytics, compras, para seleccionar futuros productos). Productos comprados

| EVENTO DE DOMINIO | ACCION DERIVADA (use case) |
| --- | --- |
| New Product Created |						
| Product quantity has been modified |
| Product price has been modified |
| New order created | (1)
| Order cancelled | (2), (4)
| Stock incremented | (3)
| Payment received | (5), (6), (2)
													