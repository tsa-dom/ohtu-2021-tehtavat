
package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Not;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {
    
    Matcher matcher;
    
    public QueryBuilder() {
        matcher = new All();
    }
    
    public QueryBuilder not() {
        this.matcher = new Not(this.matcher);
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(this.matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(this.matcher, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }
    
    public Matcher build() {
        Matcher newMatcher = this.matcher;
        this.matcher = new All();
        return newMatcher;
    }
}
