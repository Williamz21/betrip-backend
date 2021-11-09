Feature: Travel Event updating
  As a Developer
  I want to update a  Travel Event through API
  So that It can be available to use.
  Background:
    Given The EndPoint of Travel Event  "http://localhost:%d/api/v1/travelers/1/travel-events/1" is available
  @traveler-updating
  Scenario: Updating Travel Event
    When A Travel Event Update Request is sent with values 1,1,"lurin","lurin.jpg",1,"Jr.Lima","12:00 pm","24-24-24",60,"combi","345t","juan.png"
    Then A response with status 200 is received..
