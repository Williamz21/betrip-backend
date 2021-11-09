Feature: Travel Event Adding
  As a Developer
  I want to add a travel event through API
  So that It can be available to use.
  Background:
    Given The EndPoint for travel events"http://localhost:%d/api/v1/travelers/1/travel-events" is available
  @travel-event-adding
  Scenario: Add Travel-event
    When A Travel-event Request is sent with values 1,"lurin","lurin.jpg",1,"Jr.Lima","12:00 pm","24-24-24",60,"combi","345t","juan.png"
    Then A response with status 200 is received.
    And A Travel-event Resource with values 1,"lurin","lurin.jpg",1,"Jr.Lima","12:00 pm","24-24-24",60,"combi","345t","juan.png" is included in Response Body
