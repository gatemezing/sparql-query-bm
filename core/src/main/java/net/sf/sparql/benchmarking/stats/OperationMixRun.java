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

package net.sf.sparql.benchmarking.stats;

import java.util.Iterator;
import java.util.List;

import net.sf.sparql.benchmarking.operations.OperationMix;
import net.sf.sparql.benchmarking.runners.mix.OperationMixRunner;

/**
 * Represents a run of an operation mix which is comprised of some combination
 * of runs of the operations in the mix
 * <p>
 * Depending on the {@link OperationMixRunner} being used an
 * {@link OperationMixRun} may not contain {@link OperationRun} information for
 * every operation since it may not run all operations, equally some operations
 * may have been run multiple times.
 * </p>
 * 
 * @author rvesse
 * 
 */
public interface OperationMixRun extends Comparable<OperationMixRun> {

    /**
     * Gets an iterator over the runs that make up this operation mix
     * <p>
     * Implementations need not make any guarantee that the runs are returned in
     * any specific order, users <strong>should not</should> rely on the order
     * of runs in order to look up their associated operations. The recommended
     * way to do this is to use the {@link OperationRun#getId()} to obtain the
     * ID of the associated operation and then call
     * {@link OperationMix#getOperation(int)} to look up the actual operation.
     * </p>
     * 
     * @return Iterator over the runs
     */
    public abstract Iterator<OperationRun> getRuns();

    /**
     * Gets the list of run information for the given operation ID, if there are
     * no runs for that given operation within this mix run then an empty list
     * is returned.
     * 
     * @param id
     *            Operation ID
     * @return List of operation runs (possibly empty)
     */
    public abstract List<OperationRun> getRuns(int id);

    /**
     * Gets the global run order that reflects the order in which the operation
     * mixes and operations were run
     * 
     * @return Global Order
     */
    public abstract long getRunOrder();

    /**
     * Gets the number of operations runs that comprised this mix run
     * 
     * @return Number of operations run
     */
    public abstract long getRunCount();

    /**
     * Gets the total number of errors for the operation mix
     * 
     * @return Total number of errors
     */
    public abstract long getTotalErrors();

    /**
     * Gets the total number of results for the operation mix
     * 
     * @return Total number of results
     */
    public abstract long getTotalResults();

    /**
     * Gets the total runtime for the Operation Mix
     * 
     * @return Total Runtime in nanoseconds
     */
    public abstract long getTotalRuntime();

    /**
     * Gets the total response time for the Operation Mix
     * 
     * @return Total Response Time in nanoseconds
     */
    public abstract long getTotalResponseTime();

    /**
     * Gets the runtime of the operation from the set that took the shortest
     * time to run
     * 
     * @return Minimum Runtime in nanoseconds or {@link Long#MAX_VALUE} if no operations were run
     */
    public abstract long getMinimumRuntime();

    /**
     * Gets the ID of the operation that took the shortest time to run
     * 
     * @return ID of the Operation with the Minimum Runtime or -1 if no operations were run
     */
    public abstract int getMinimumRuntimeOperationID();

    /**
     * Gets the runtime of the operation from the set that took the longest time
     * to run
     * 
     * @return Maximum Runtime in nanoseconds or {@link Long#MIN_VALUE} if no operations were run
     */
    public abstract long getMaximumRuntime();

    /**
     * Gets the ID of the operation that took the longest time to run
     * 
     * @return ID of the operation with Maximum Runtime or -1 if no operations were run
     */
    public abstract int getMaximumRuntimeOperationID();

}