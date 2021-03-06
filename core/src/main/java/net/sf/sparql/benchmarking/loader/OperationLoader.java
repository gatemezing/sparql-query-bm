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

package net.sf.sparql.benchmarking.loader;

import java.io.File;
import java.io.IOException;

import net.sf.sparql.benchmarking.operations.Operation;

/**
 * Interface for operation loaders
 * <p>
 * Each loader is expected to load only a single type of operation since part of
 * the interface contract provides help information to users about the operation
 * that can be loaded.
 * </p>
 * 
 * @author rvesse
 * 
 */
public interface OperationLoader {

    /**
     * Load an operation described by the given arguments
     * 
     * @param baseDir
     *            Base directory
     * @param args
     *            Arguments
     * @return Operation
     * @throws IOException
     */
    public abstract Operation load(File baseDir, String[] args) throws IOException;

    /**
     * Gets the name that this loader prefers to be referenced by
     * <p>
     * This is the preferred name used in certain circumstances such as when
     * registering using
     * {@link OperationLoaderRegistry#addLoader(OperationLoader)} but users may
     * register a loader with any name they want.
     * </p>
     * 
     * @return Preferred name
     */
    public abstract String getPreferredName();

    /**
     * Gets a description of the operation that this loader loads
     * 
     * @return Description of the operation
     */
    public abstract String getDescription();

    /**
     * Gets a description of the configuration arguments that this loader
     * expects to receive
     * 
     * @return Arguments
     */
    public abstract OperationLoaderArgument[] getArguments();
}
