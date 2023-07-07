/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu001844.framework;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tonymushah
 */
public class ModelView extends HashMap<String, Object> {
    private String url;
    private Set<Cookie> cookie;
    private HashMap<String, String> headers;
    private boolean isJson = false;

    public boolean isIsJson() {
        return isJson;
    }

    public void setIsJson(boolean isJson) {
        this.isJson = isJson;
    }
    
    public Set<Cookie> getCookie() {
        return cookie;
    }

    public void setCookie(Set<Cookie> cookie) {
        this.cookie = cookie;
    }
    

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ModelView(String url) {
        this.setUrl(url);
    }

    public ModelView() {
        this.setHeaders(new HashMap());
        this.setCookie(new HashSet());
    }

    public ModelView(String url, Map<? extends String, ? extends Object> m) {
        super(m);
        this.setUrl(url);
        this.setHeaders(new HashMap());
        this.setCookie(new HashSet());
    }

    public HashMap<String, Object> getData(){
        return this;
    }
    
    public HashMap<String, Object> cloneData(){
        return (HashMap<String, Object>) super.clone();
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super Object, ? extends Object> function) {
        super.replaceAll(function); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super Object> action) {
        super.forEach(action); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object merge(String key, Object value, BiFunction<? super Object, ? super Object, ? extends Object> remappingFunction) {
        return super.merge(key, value, remappingFunction); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object compute(String key, BiFunction<? super String, ? super Object, ? extends Object> remappingFunction) {
        return super.compute(key, remappingFunction); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ? extends Object> remappingFunction) {
        return super.computeIfPresent(key, remappingFunction); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object computeIfAbsent(String key, Function<? super String, ? extends Object> mappingFunction) {
        return super.computeIfAbsent(key, mappingFunction); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object replace(String key, Object value) {
        return super.replace(key, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean replace(String key, Object oldValue, Object newValue) {
        return super.replace(key, oldValue, newValue); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean remove(Object key, Object value) {
        return super.remove(key, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object putIfAbsent(String key, Object value) {
        return super.putIfAbsent(key, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        return super.getOrDefault(key, defaultValue); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return super.entrySet(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Collection<Object> values() {
        return super.values(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Set<String> keySet() {
        return super.keySet(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean containsValue(Object value) {
        return super.containsValue(value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void clear() {
        super.clear(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object remove(Object key) {
        return super.remove(key); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        super.putAll(m); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object put(String key, Object value) {
        return super.put(key, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(key); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object get(Object key) {
        return super.get(key); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int size() {
        return super.size(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
