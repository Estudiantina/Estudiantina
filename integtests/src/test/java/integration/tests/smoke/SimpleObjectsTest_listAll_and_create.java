/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package integration.tests.smoke;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import integration.tests.SimpleAppIntegTest;

import java.util.List;

import dom.simple.SimpleObject;
import dom.simple.SimpleObjects;
import fixture.simple.SimpleObjectsFixture;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import org.apache.isis.applib.clock.Clock;

public class SimpleObjectsTest_listAll_and_create extends SimpleAppIntegTest {

    private SimpleObjects simpleObjects;

    @Before
    public void setUp() throws Exception {

        scenarioExecution().install(new SimpleObjectsFixture());
        
        simpleObjects = wrap(service(SimpleObjects.class));
    }

    
    @Test
    public void listAll() throws Exception {

        final List<SimpleObject> all = simpleObjects.listAll();
        assertThat(all.size(), is(3));
        
        SimpleObject simpleObject = wrap(all.get(0));
        assertThat(simpleObject.getName(), is("Foo"));
    }
    
    @Test
    public void create() throws Exception {
        
        simpleObjects.create("Faz");
        
        final List<SimpleObject> all = simpleObjects.listAll();
        assertThat(all.size(), is(4));
    }

}