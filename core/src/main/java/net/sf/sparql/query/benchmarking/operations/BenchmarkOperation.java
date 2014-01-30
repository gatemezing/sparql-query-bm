/** 
 * Copyright 2011-2012 Cray Inc. All Rights Reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name Cray Inc. nor the names of its contributors may be
 *   used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 **/
package net.sf.sparql.query.benchmarking.operations;

import java.util.Iterator;

import net.sf.sparql.query.benchmarking.Benchmarker;
import net.sf.sparql.query.benchmarking.stats.OperationRun;

/**
 * Represents a single operation within a benchmark
 * 
 * @author rvesse
 * 
 */
public interface BenchmarkOperation {

    /**
     * Gets the Name of the Operation (typically the filename)
     * 
     * @return Name
     */
    public abstract String getName();

    /**
     * Gets an iterator over the operation runs
     * 
     * @return Runs of the operation
     */
    public abstract Iterator<OperationRun> getRuns();

    /**
     * Gets the total runtime for the query over all runs
     * 
     * @return Total Runtime in nanoseconds
     */
    public abstract long getTotalRuntime();

    /**
     * Gets the actual runtime for the operation over all runs (takes into
     * account operations that run in parallel)
     * 
     * @return Actual Runtime in nanoseconds
     */
    public abstract long getActualRuntime();

    /**
     * Gets the total response time for the operations over all runs
     * <p>
     * For non-streaming operations this will likely be equal to
     * {@link #getTotalRuntime()}
     * </p>
     * 
     * @return Total response time
     */
    public abstract long getTotalResponseTime();

    /**
     * Gets the average runtime for the operation over all runs (arithmetic
     * mean) based on the total runtime
     * 
     * @return Arithmetic Average Runtime in nanoseconds
     */
    public abstract long getAverageRuntime();

    /**
     * Gets the average response time over all runs (arithmetic mean) based on
     * the total response time
     * <p>
     * For non-streaming operations this will likely be equal to
     * {@link #getAverageRuntime()}
     * </p>
     * 
     * @return Average Response Time in nanoseconds
     */
    public abstract long getAverageResponseTime();

    /**
     * Gets the average runtime for the operation over all runs (geometric mean)
     * based on the total runtime
     * 
     * @return Geometric Average Runtime in nanoseconds
     */
    public abstract double getGeometricAverageRuntime();

    /**
     * Gets average runtime for the operation over all runs (arithmetic mean)
     * based on the actual runtime
     * 
     * @return Arithmetic Average runtime in nanoseconds
     */
    public abstract long getActualAverageRuntime();

    /**
     * Gets the minimum runtime for this operation over all runs
     * 
     * @return Minimum Runtime in nanoseconds
     */
    public abstract long getMinimumRuntime();

    /**
     * Gets the maximum runtime for this operation over all runs
     * 
     * @return Maximum Runtime in nanoseconds
     */
    public abstract long getMaximumRuntime();

    /**
     * Gets the variance for the operation runtimes
     * 
     * @return Runtime Variance in nanoseconds
     */
    public abstract double getVariance();

    /**
     * Gets the standard deviation for operation runtime
     * 
     * @return Runtime Standard Deviation in nanoseconds
     */
    public abstract double getStandardDeviation();

    /**
     * Calculates how many times this operation could be executed
     * single-threaded per second based upon the average runtime of the
     * operation
     * 
     * @return Operations per Second
     */
    public abstract double getOperationsPerSecond();

    /**
     * Calculates how many times this operation could be executed multi-threaded
     * per second based upon the {@link #getActualAverageRuntime()}
     * 
     * @return Actual Operations per Second
     */
    public abstract double getActualOperationsPerSecond();

    /**
     * Calculates how many times this operation could be executed
     * single-threaded per hour based upon the average runtime of the operation
     * 
     * @return Operations per Hour
     */
    public abstract double getOperationsPerHour();

    /**
     * Calculates how many times this operation could be executed multi-threaded
     * per hour based upon the {@link #getActualAverageRuntime()}
     * 
     * @return Actual Operations per Hour
     */
    public abstract double getActualOperationsPerHour();

    /**
     * Runs the operation recording the statistics as a {@link OperationRun}
     * 
     * @param b
     *            Benchmarker
     * @return Operation Run statistics
     */
    public abstract OperationRun run(Benchmarker b);

    /**
     * Clears all run statistics
     */
    public abstract void clear();

    /**
     * Trims the best and worst N runs
     * 
     * @param outliers
     *            Number of outliers to trim
     */
    public abstract void trim(int outliers);

}