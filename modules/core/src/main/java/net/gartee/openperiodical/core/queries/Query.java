package net.gartee.openperiodical.core.queries;

public abstract class Query<TCriteria extends Criteria, TResult> {
    public abstract TResult execute(TCriteria criteria);
}