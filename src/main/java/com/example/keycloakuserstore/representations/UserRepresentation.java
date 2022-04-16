package com.example.keycloakuserstore.representations;

import com.example.keycloakuserstore.dao.UserDAO;
import com.example.keycloakuserstore.models.User;
import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class UserRepresentation extends AbstractUserAdapterFederatedStorage {
    private User userEntity;
    private UserDAO userDAO;
    Logger logger = Logger.getLogger(UserDAO.class.getName());
    public UserRepresentation(KeycloakSession session,
                              RealmModel realm,
                              ComponentModel storageProviderModel,
                              User userEntity,
                              UserDAO userDAO) {
        super(session, realm, storageProviderModel);
        this.userEntity = userEntity;
        this.userDAO = userDAO;
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public void setUsername(String username) {
        userEntity.setUsername(username);
        userEntity = userDAO.updateUser(userEntity);
    }

    @Override
    public void setEmail(String email) {
        userEntity.setEmail(email);
        userEntity = userDAO.updateUser(userEntity);
    }

    @Override
    public String getEmail() {
        return userEntity.getEmail();
    }

    @Override
    public void setSingleAttribute(String name, String value) {
        if (name.equals("phone")) {
            userEntity.setPhone(value);
        }else if (name.equals("position")) {
            userEntity.setPosition(value);
        }else if (name.equals("status")) {
            userEntity.setStatus(value);
        }else if (name.equals("type")) {
            userEntity.setType(value);
        } else {
            super.setSingleAttribute(name, value);
        }
    }

    @Override
    public void removeAttribute(String name) {
        if (name.equals("phone")) {
            userEntity.setPhone(null);
        }else if (name.equals("position")) {
            userEntity.setPosition(null);
        }else if (name.equals("status")) {
            userEntity.setStatus(null);
        }else if (name.equals("type")) {
            userEntity.setType(null);
        }else {
            super.removeAttribute(name);
        }
        userEntity = userDAO.updateUser(userEntity);
    }

    @Override
    public void setAttribute(String name, List<String> values) {
        if (name.equals("phone")) {
            userEntity.setPhone(values.get(0));
        }else if (name.equals("position")) {
            userEntity.setPosition(values.get(0));
        }else if (name.equals("status")) {
            userEntity.setStatus(values.get(0));
        }else if (name.equals("type")) {
            userEntity.setType(values.get(0));
        } else {
            super.setAttribute(name, values);
        }
        userEntity = userDAO.updateUser(userEntity);
    }

    @Override
    public String getFirstAttribute(String name) {
        if (name.equals("phone")) {
            return userEntity.getPhone();
        }else if (name.equals("position")) {
            return userEntity.getPosition();
        }else if (name.equals("status")) {
            return userEntity.getStatus();
        }else if (name.equals("type")) {
            return userEntity.getType();
        } else {
            return super.getFirstAttribute(name);
        }
    }

    @Override
    public Map<String, List<String>> getAttributes() {
        Map<String, List<String>> attrs = super.getAttributes();
        MultivaluedHashMap<String, String> all = new MultivaluedHashMap<>();
        all.putAll(attrs);
        all.add("phone", userEntity.getPhone());
        all.add("position", userEntity.getPosition());
        all.add("status", userEntity.getStatus());
        all.add("type", userEntity.getType());
        return all;
    }

    @Override
    public List<String> getAttribute(String name) {
        if (name.equals("phone")) {
            List<String> phone = new LinkedList<>();
            phone.add(userEntity.getPhone());
            return phone;
        }else if (name.equals("position")) {
            List<String> position = new LinkedList<>();
            position.add(userEntity.getPosition());
            return position;
        }else if (name.equals("status")) {
            List<String> status = new LinkedList<>();
            status.add(userEntity.getStatus());
            return status;
        }else if (name.equals("type")) {
            List<String> type = new LinkedList<>();
            type.add(userEntity.getType());
            return type;
        } else {
            return super.getAttribute(name);
        }
    }

    @Override
    public String getId() {
        logger.info("UserRepresentation-getId(id: " + userEntity.getId() + ")");
        logger.info("UserRepresentation-getId(id-toString: " + userEntity.getId().toString() + ")");
        String result = StorageId.keycloakId(storageProviderModel, userEntity.getId().toString());
        logger.info("UserRepresentation-result = " + result);
        return result;
        // return StorageId.keycloakId(storageProviderModel, userEntity.getId());//0022
    }

    public String getPassword() {
        return userEntity.getPassword();
    }

    public void setPassword(String password) {
        userEntity.setPassword(password);
        userEntity = userDAO.updateUser(userEntity);
    }
}
