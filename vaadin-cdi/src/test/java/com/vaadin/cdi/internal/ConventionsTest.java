/*
 * Copyright 2012 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.cdi.internal;

import com.vaadin.cdi.integrationtests.uis.PlainUI;
import static com.vaadin.cdi.internal.Conventions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ConventionsTest {

    @Test
    public void normalizeLowerFirstCase() {
        String origin = "LoginPage";
        String expected = "loginPage";
        String actual = firstToLower(origin);
        assertThat(actual, is(expected));
    }

    @Test
    public void lowerFirstCaseWithOneCharacter() {
        String origin = "A";
        String expected = "a";
        String actual = firstToLower(origin);
        assertThat(actual, is(expected));
    }

    @Test
    public void normalizeNothingToDo() {
        String origin = "loginPage";
        String expected = "loginPage";
        String actual = firstToLower(origin);
        assertThat(actual, is(expected));
    }

    /* TODO Re-enable
    @Test
    public void extractViewNameUsingPath() {
        String expected = "custom";
        String actual = deriveMappingForView(OneAndOnlyViewWithPath.class);
        assertThat(actual, is(expected));
    }

    @Test
    public void extractViewNameUsingConvention() {
        String expected = "oneAndOnlyViewWithoutPath";
        String actual = deriveMappingForView(OneAndOnlyViewWithoutPath.class);
        assertThat(actual, is(expected));
    }

    @Test
    public void extractViewNameUsingConventionWithoutAnnotation() {
        String expected = "oneAndOnlyViewWithoutPathAndAnnotation";
        String actual = deriveMappingForView(OneAndOnlyViewWithoutPathAndAnnotation.class);
        assertThat(actual, is(expected));
    }*/

    @Test
    public void extractUIPathUsingConvention() {
        String expected = "plainUI";
        String actual = deriveMappingForUI(PlainUI.class);
        assertThat(actual, is(expected));
    }

/* TODO Re-enable    @Test
    public void extractUIPathUsingAnnotation() {
        String expected = "plainUI";
        String actual = deriveMappingForUI(PlainColidingAlternativeUI.class);
        assertThat(actual, is(expected));
    }*/

    @Test
    public void uiAnnotationNotPresent() {
        final String uiPath = deriveMappingForUI(String.class);
        assertNull(uiPath);
    }

    @Test
    public void uriWithJustUINoEndingSlash() {
        String origin = "http://localhost:8080/hello-cdi/uIWithViewUI";
        String expected = "uIWithViewUI";
        String actual = deriveUIMappingFromRequestPath(origin);
        assertThat(actual, is(expected));
    }

    @Test
    public void uriWithJustUIWithEndingSlash() {
        String origin = "http://localhost:8080/hello-cdi/uIWithViewUI/";
        String expected = "uIWithViewUI";
        String actual = deriveUIMappingFromRequestPath(origin);
        assertThat(actual, is(expected));
    }

    @Test
    public void uriWithUIAndViewWithoutEndingSlash() {
        String origin = "http://localhost:8080/hello-cdi/uIWithViewUI/!helloView";
        String expected = "uIWithViewUI";
        String actual = deriveUIMappingFromRequestPath(origin);
        assertThat(actual, is(expected));
    }

    @Test
    public void uriWithUIAndViewWithEndingSlash() {
        String origin = "http://localhost:8080/hello-cdi/uIWithViewUI/!helloView/";
        String expected = "uIWithViewUI";
        String actual = deriveUIMappingFromRequestPath(origin);
        assertThat(actual, is(expected));
    }
}
