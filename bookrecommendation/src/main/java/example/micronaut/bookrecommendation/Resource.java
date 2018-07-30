package example.micronaut.bookrecommendation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//ghetto hateoas
public class Resource<T> implements Serializable {

    private Map<String, Link> _links;
    private T content;

    Resource(T content, Map<String, Link> _links) {
        this._links = _links;
        this.content = content;
    }

    Resource(T content) {
        this(content, new HashMap<>());
    }

    public Map<String, Link> get_links() {
        return _links;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void addLink(String name, String uri) {
        this._links.put(name, new Link(uri));
    }

    public void addLinks(Map<String, Link> links) {
        this._links.putAll(links);
    }
}
