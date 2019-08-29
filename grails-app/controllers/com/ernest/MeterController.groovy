package com.ernest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MeterController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Meter.list(params), model:[meterCount: Meter.count()]
    }

    def show(Meter meter) {
        respond meter
    }

    def create() {
        respond new Meter(params)
    }

    @Transactional
    def save(Meter meter) {
        if (meter == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (meter.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond meter.errors, view:'create'
            return
        }

        meter.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'meter.label', default: 'Meter'), meter.id])
                redirect meter
            }
            '*' { respond meter, [status: CREATED] }
        }
    }

    def edit(Meter meter) {
        respond meter
    }

    @Transactional
    def update(Meter meter) {
        if (meter == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (meter.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond meter.errors, view:'edit'
            return
        }

        meter.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'meter.label', default: 'Meter'), meter.id])
                redirect meter
            }
            '*'{ respond meter, [status: OK] }
        }
    }

    @Transactional
    def delete(Meter meter) {

        if (meter == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        meter.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'meter.label', default: 'Meter'), meter.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'meter.label', default: 'Meter'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
