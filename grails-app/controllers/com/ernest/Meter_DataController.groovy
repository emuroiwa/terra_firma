package com.ernest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Meter_DataController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Meter_Data.list(params), model:[meter_DataCount: Meter_Data.count()]
    }

    def show(Meter_Data meter_Data) {
        respond meter_Data
    }

    def create() {
        respond new Meter_Data(params)
    }

    @Transactional
    def save(Meter_Data meter_Data) {
        if (meter_Data == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (meter_Data.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond meter_Data.errors, view:'create'
            return
        }

        meter_Data.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'meter_Data.label', default: 'Meter_Data'), meter_Data.id])
                redirect meter_Data
            }
            '*' { respond meter_Data, [status: CREATED] }
        }
    }

    def edit(Meter_Data meter_Data) {
        respond meter_Data
    }

    @Transactional
    def update(Meter_Data meter_Data) {
        if (meter_Data == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (meter_Data.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond meter_Data.errors, view:'edit'
            return
        }

        meter_Data.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'meter_Data.label', default: 'Meter_Data'), meter_Data.id])
                redirect meter_Data
            }
            '*'{ respond meter_Data, [status: OK] }
        }
    }

    @Transactional
    def delete(Meter_Data meter_Data) {

        if (meter_Data == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        meter_Data.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'meter_Data.label', default: 'Meter_Data'), meter_Data.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'meter_Data.label', default: 'Meter_Data'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
