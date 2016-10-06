package com.ryanair.web

import spock.lang.Specification
import spock.lang.Unroll

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo

/**
 * It is testing if URLs are opening properly.
 */
class UrlOpenerSpec extends Specification {

    @Unroll
    def "URL #url is opened it redirects to https"() {
        when:
        DriverHelper.getDriverFromProps().navigate().to(url);

        then:
        assertThat(DriverHelper.getDriverFromProps().getCurrentUrl(), equalTo(expectedUrl))

        where:
        url                                 |   expectedUrl
        "http://ryanair.com/ie/en/"         |   "https://www.ryanair.com/ie/en/"
        "http://www.ryanair.com/ie/en/"     |   "https://www.ryanair.com/ie/en/"
        "https://www.ryanair.com/ie/en/"    |   "https://www.ryanair.com/ie/en/"
    }


    def setupSpec() {
        DriverHelper.getDriverFromProps();
    }

    def cleanupSpec() {
        DriverHelper.getRidOfDriver();
    }
}
