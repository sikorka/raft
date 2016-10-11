package com.ryanair.web

import com.ryanair.web.core.WebSpecification
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo
import spock.lang.Unroll

/**
 * It is testing if URLs are opening properly.
 */
class UrlOpenerSpec extends WebSpecification {

    @Unroll
    def "#url redirects to #expectedUrl"() {
        when:
        getDriver().navigate().to(url);

        then:
        takeScreenShot();
        assertThat(getDriver().getCurrentUrl(), equalTo(expectedUrl));

        where:
        url                              | expectedUrl
        "http://ryanair.com/ie/en/"      | "https://www.ryanair.com/ie/en/"
        "http://www.ryanair.com/ie/en/"  | "https://www.ryanair.com/ie/en/"
        "https://www.ryanair.com/ie/en/" | "https://www.ryanair.com/ie/en/"
    }
}