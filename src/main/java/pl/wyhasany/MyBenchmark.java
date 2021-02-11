/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package pl.wyhasany;

import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

@Fork(1)
@Warmup(iterations = 1, time = 10, timeUnit = SECONDS)
@Measurement(iterations = 4, time = 10, timeUnit = SECONDS)
@BenchmarkMode(AverageTime)
@OutputTimeUnit(NANOSECONDS)
@State(Scope.Thread)
public class MyBenchmark {

    ValueClass valueClass1;
    ValueClass valueClass2;

    Record recordValue1;
    Record recordValue2;

    @Setup
    public void prepare() {
        valueClass1 = new ValueClass("value-class");
        valueClass2 = new ValueClass("value-class2");
        recordValue1 = new Record("record");
        recordValue2 = new Record("record2");
    }

    @Benchmark
    public void base() {}

    @Benchmark
    public String value_class_to_string() {
        return valueClass1.toString();
    }

    @Benchmark
    public String record_to_string() {
        return recordValue1.toString();
    }

    @Benchmark
    public boolean equals_value() {
        return valueClass1.equals(valueClass2);
    }

    @Benchmark
    public boolean equals_record() {
        return recordValue1.equals(recordValue2);
    }

    @Benchmark
    public int value_hash_code() {
        return valueClass1.hashCode();
    }

    @Benchmark
    public int record_hash_code() {
        return recordValue1.hashCode();
    }
}
