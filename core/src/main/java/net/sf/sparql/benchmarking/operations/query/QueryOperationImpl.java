/*
Copyright 2011-2014 Cray Inc. All Rights Reserved

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

 * Redistributions of source code must retain the above copyright
  notice, this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright
  notice, this list of conditions and the following disclaimer in the
  documentation and/or other materials provided with the distribution.

 * Neither the name Cray Inc. nor the names of its contributors may be
  used to endorse or promote products derived from this software
  without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 
 */

package net.sf.sparql.benchmarking.operations.query;

import net.sf.sparql.benchmarking.operations.AbstractOperation;
import net.sf.sparql.benchmarking.operations.OperationCallable;
import net.sf.sparql.benchmarking.options.Options;
import net.sf.sparql.benchmarking.runners.Runner;
import net.sf.sparql.benchmarking.stats.QueryRun;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;

/**
 * Represents a query operation
 * 
 * @author rvesse
 */
public class QueryOperationImpl extends AbstractOperation<QueryRun> implements QueryOperation {

    private Query query;
    private String origQueryStr;

    /**
     * Creates a new Query
     * 
     * @param name
     *            Name of the query
     * @param queryString
     *            Query string
     */
    public QueryOperationImpl(String name, String queryString) {
        super(name);
        this.origQueryStr = queryString;
        this.query = QueryFactory.create(this.origQueryStr);
    }

    @Override
    public Query getQuery() {
        return this.query;
    }

    @Override
    public String getQueryString() {
        return this.origQueryStr;
    }

    @Override
    public <T extends Options> boolean canRun(Runner<T> runner, T options) {
        if (options.getQueryEndpoint() == null) {
            runner.reportProgress(options, "Queries cannot run with no query endpoint specified");
            return false;
        }
        return true;
    }

    @Override
    protected <T extends Options> OperationCallable<T, QueryRun> createCallable(Runner<T> runner, T options) {
        return new QueryCallable<T>(this.query, runner, options);
    }

    @Override
    protected QueryRun createErrorInformation(String message, long runtime) {
        return new QueryRun(message, runtime);
    }

    /**
     * Gets the string representation (i.e. the name) of the operation
     */
    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public String getType() {
        return "SPARQL Query";
    }

    @Override
    public String getContentString() {
        return this.getQueryString();
    }
}