package net.gartee.openperiodical.core.queries;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SomeRandomQuery extends Query<SomeRandomCriteria, List<String>> {
    public List<String> execute(SomeRandomCriteria criteria) {
        return null;
    }
}
