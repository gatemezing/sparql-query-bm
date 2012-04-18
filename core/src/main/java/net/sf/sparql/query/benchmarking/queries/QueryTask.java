/**
 * Copyright 2012 Robert Vesse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * you may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.sparql.query.benchmarking.queries;

import java.util.concurrent.FutureTask;

import net.sf.sparql.query.benchmarking.stats.QueryRun;


/**
 * A Query Task that can be executed
 * @author rvesse
 *
 */
public class QueryTask extends FutureTask<QueryRun> {
	
	/**
	 * Creates a new Query Task
	 * @param q Query
	 * @param b Benchmarker
	 */
	public QueryTask(QueryRunner qr) {
		super(qr);
	}


}