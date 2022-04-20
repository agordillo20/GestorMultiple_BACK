package com.agordillo.GestorMultiple.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agordillo.GestorMultiple.Common.GenericServiceImplementation;
import com.agordillo.GestorMultiple.models.Carrito;
import com.agordillo.GestorMultiple.models.Linea_Carrito;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

@Service
public class CarritoService extends GenericServiceImplementation<Carrito, Carrito> {

	@Autowired
	private Firestore firestore;

	@Override
	public CollectionReference getCollection() {
		return firestore.collection("carrito");
	}

	public Carrito getCarritoByUserId(String userId) throws Exception {
		List<Carrito> filtrado = this.getAll().stream()
				.filter((Carrito carrito) -> carrito.getUser().equals(userId) && carrito.isActive()).toList();
		return filtrado.isEmpty() ? null : filtrado.get(0);
	}

	public void deleteLineaCarrito(Carrito carrito, Linea_Carrito linea) throws Exception {
		carrito.getLineas_productos().removeIf((Linea_Carrito linea_a) -> Linea_Carrito.equals(linea_a, linea));
		if(carrito.getLineas_productos().isEmpty()) {
			delete(carrito.getId());
		}else {
			save(carrito, carrito.getId());
		}
	}

}
