package example.micronaut.bookrecommendation;

import java.util.Map;

public class ResourceBuilder<T> {

    private Resource<T> content;

    ResourceBuilder(T content) {
        this.content = new Resource<>(content);
    }

    ResourceBuilder<T> withLink(String name, String uri) {
        this.content.addLink(name, uri);
        return this;
    }

    ResourceBuilder<T> withLinks(Map<String, Link> links) {
        this.content.addLinks(links);
        return this;
    }

    Resource<T> build() {
        return content;
    }

}
