package com.ernest

import grails.test.mixin.*
import spock.lang.*

@TestFor(Meter_Data_SummaryController)
@Mock(Meter_Data_Summary)
class Meter_Data_SummaryControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.meter_Data_SummaryList
            model.meter_Data_SummaryCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.meter_Data_Summary!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def meter_Data_Summary = new Meter_Data_Summary()
            meter_Data_Summary.validate()
            controller.save(meter_Data_Summary)

        then:"The create view is rendered again with the correct model"
            model.meter_Data_Summary!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            meter_Data_Summary = new Meter_Data_Summary(params)

            controller.save(meter_Data_Summary)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/meter_Data_Summary/show/1'
            controller.flash.message != null
            Meter_Data_Summary.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def meter_Data_Summary = new Meter_Data_Summary(params)
            controller.show(meter_Data_Summary)

        then:"A model is populated containing the domain instance"
            model.meter_Data_Summary == meter_Data_Summary
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def meter_Data_Summary = new Meter_Data_Summary(params)
            controller.edit(meter_Data_Summary)

        then:"A model is populated containing the domain instance"
            model.meter_Data_Summary == meter_Data_Summary
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/meter_Data_Summary/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def meter_Data_Summary = new Meter_Data_Summary()
            meter_Data_Summary.validate()
            controller.update(meter_Data_Summary)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.meter_Data_Summary == meter_Data_Summary

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            meter_Data_Summary = new Meter_Data_Summary(params).save(flush: true)
            controller.update(meter_Data_Summary)

        then:"A redirect is issued to the show action"
            meter_Data_Summary != null
            response.redirectedUrl == "/meter_Data_Summary/show/$meter_Data_Summary.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/meter_Data_Summary/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def meter_Data_Summary = new Meter_Data_Summary(params).save(flush: true)

        then:"It exists"
            Meter_Data_Summary.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(meter_Data_Summary)

        then:"The instance is deleted"
            Meter_Data_Summary.count() == 0
            response.redirectedUrl == '/meter_Data_Summary/index'
            flash.message != null
    }
}
