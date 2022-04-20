package com.agordillo.GestorMultiple.Common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public abstract class GenericServiceImplementation<I,O> implements GenericServiceInterface<I,O>{

	private Class<O> clazz;
	
	@SuppressWarnings("unchecked")
	public GenericServiceImplementation(){
		this.clazz = ((Class<O>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
	}
	
	@Override
	public String save(I entity, String id) throws Exception {
		if(id==null || id.length()==0) {
			return getCollection().add(entity).get().getId();
		}else {
			DocumentReference reference = getCollection().document(id);
			reference.set(entity);
			return reference.getId();
		}
	}

	@Override
	public String save(I entity) throws Exception {
		return this.save(entity, null);
	}

	@Override
	public void delete(String id) throws Exception {
		getCollection().document(id).delete().get();
	}

	@Override
	public O get(String id) throws Exception {
		ApiFuture<DocumentSnapshot> reference = getCollection().document(id).get();
		if(reference.get().exists()) {
			O object = reference.get().toObject(clazz);
			PropertyUtils.setProperty(object,"id",reference.get().getId());
			return object;
			//return reference.get().toObject(clazz);
		}
		return null;
	}

	@Override
	public Map<String, Object> getAsMap(String id) throws Exception {
		ApiFuture<DocumentSnapshot> apiFuture = getCollection().document(id).get();
		DocumentSnapshot documentSnapshot = apiFuture.get();
		if(documentSnapshot.exists()) {
			return documentSnapshot.getData();
		}
		return null;
	}

	@Override
	public List<O> getAll() throws Exception {
		List<O> result = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = getCollection().get().get().getDocuments();
		documents.forEach(doc ->{
			var object = doc.toObject(clazz);
			try {
				PropertyUtils.setProperty(object, "id", doc.getId());
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
			result.add(object);
		});
		return result;
	}
	
	@Override
	public List<String> getKeys() throws Exception{
		List<String> keys = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = getCollection().get().get().getDocuments();
		documents.forEach(doc->keys.add(doc.getId()));
		return keys;
	}
    
    public abstract CollectionReference getCollection();
}
