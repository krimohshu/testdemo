<h2>Maven Command</h2>
<h3>To Run Tests</h3>
mvn clean test -Dspring.profiles.active=qa
<h3>To Run Application</h3>
Application on 9095 port: mvn clean spring-boot:run -Dspring.profiles.active=qa


<h2>Junit Tests Information TDD </h2>
<p>Total 14 test implemented as of now.
<p>P0, P1, All-tests Suite can be run
<p>Custom exception and dto added, but Taxcalc can be MVC/API oriented implementation 
Springboot, JPA ,AOP etc and can be tested with SprintbootTest,Mockito, Wiremock,TestDataJPA and restassured.


<h2>UI Automation Information - In BDD </h2>
To test search functionality of https://pokesearch-client.herokuapp.com/
<h2> Framework Highlights </h2>
This is demo repo and created in few hours so not everything is there:
<p>@DataTableType annotation used for typeRegister ( cucumber-4 to cucumber-5 improvement )
<p> Framework build to mimic domain language 
<p> Abstract Page cater generic way to handle objects such as dropdown, check box, goto, wait properties
<p>Framework support to replace the object definition on the fly. It will help to user on By element toreuse for entire page
<p>Common issues of automation such as spinner, page refresh has been handled in this solution
<p>Browserstack support has been implemented and checked. It is disable
<p>Added cucumber timeline report in cucumberOptions and surefire threadcount. Alluer or other report can be added.
<p>Used latest  Spring2, Java8 & cucumber5
<p>Logback is added , message can be embedded into Scenario and html report
<p>Different env and other property source can be enabled with spring profiles
<p>AssertJ used for soft and fluent assertions
<p>Spring bring lots of coding benefit and easy to implement code

<h2>Future Enhancement </h2>
<p>Data creation can be develop as seperate microservice
<p>Json manipulation need to be enhanced
<p>Parametrize Jenkins file support
<p>API automation should be used as different module and DSL need to go in common module
<p> mobile automation need to be a separate module and reuse common lib as much as possible
<p> Parllelization need to be added
<p>Rule engine such as easy-rule need to be implemented
<p>Jenkins pipeline need to enhance with other stages e.g. security, performance and code analysis
<p>Reporting need to be enhanced (Clucumber or donought)
<p>Custom Exception need to be added in great detail
<p>AssertJ will be incorporated for fluent assertion
<p>Abstraction of Element need to be incorporated
<p>Framework tweak to run on grid, browserstack, Linux, iOS and Windows env
<p>Utilities - Randomization, Javafaker, Date, collections, Json, Strings, pdf, csv , excel etc need to be enhanced
<p>Abstract layer of object identification need to be incorporated into solution
<p>One verification can verify all the element of the result. e.g. after filter framework verify each result one by one
...


<h2>Technology/api/plugins</h2>
<p>cucumber - 5.4.1
<p>Spring - 2.0.3.RELEASE
<p>Runner using Junit
<p>Maven, surefire
<p>Parllel - Native cucumber support
<p>Test can run on dev, preprod or qa env
<p>JPA setting disabled , but can added back by uncommnted prop files
<p>RestAssure , restspecification via springbootRunner. But good if project have seperate API module
