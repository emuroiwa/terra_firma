package com.ernest

import grails.test.mixin.*
import spock.lang.*

@TestFor(Meter_DataController)
@Mock(Meter_Data)
class Meter_DataControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.meter_DataList
            model.meter_DataCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.meter_Data!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def meter_Data = new Meter_Data()
            meter_Data.validate()
            controller.save(meter_Data)

        then:"The create view is rendered again with the correct model"
            model.meter_Data!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            meter_Data = new Meter_Data(params)

            controller.save(meter_Data)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/meter_Data/show/1'
            controller.flash.message != null
            Meter_Data.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def meter_Data = new Meter_Data(params)
            controller.show(meter_Data)

        then:"A model is populated containing the domain instance"
            model.meter_Data == meter_Data
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def meter_Data = new Meter_Data(params)
            controller.edit(meter_Data)

        then:"A model is populated containing the domain instance"
            model.meter_Data == meter_Data
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/meter_Data/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def meter_Data = new Meter_Data()
            meter_Data.validate()
            controller.update(meter_Data)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.meter_Data == meter_Data

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            meter_Data = new Meter_Data(params).save(flush: true)
            controller.update(meter_Data)

        then:"A redirect is issued to the show action"
            meter_Data != null
            response.redirectedUrl == "/meter_Data/show/$meter_Data.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/meter_Data/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def meter_Data = new Meter_Data(params).save(flush: true)

        then:"It exists"
            Meter_Data.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(meter_Data)

        then:"The instance is deleted"
            Meter_Data.count() == 0
            response.redirectedUrl == '/meter_Data/index'
            flash.message != null
    }
}
