package com.ernest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Meter_Daily_SummaryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Meter_Daily_Summary.list(params), model:[meter_Daily_SummaryCount: Meter_Daily_Summary.count()]
    }

    def show(Meter_Daily_Summary meter_Daily_Summary) {
        respond meter_Daily_Summary
    }

    def create() {
        respond new Meter_Daily_Summary(params)
    }

    @Transactional
    def save(Meter_Daily_Summary meter_Daily_Summary) {
        if (meter_Daily_Summary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (meter_Daily_Summary.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond meter_Daily_Summary.errors, view:'create'
            return
        }

        meter_Daily_Summary.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'meter_Daily_Summary.label', default: 'Meter_Daily_Summary'), meter_Daily_Summary.id])
                redirect meter_Daily_Summary
            }
            '*' { respond meter_Daily_Summary, [status: CREATED] }
        }
    }

    def edit(Meter_Daily_Summary meter_Daily_Summary) {
        respond meter_Daily_Summary
    }

    @Transactional
    def update(Meter_Daily_Summary meter_Daily_Summary) {
        if (meter_Daily_Summary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (meter_Daily_Summary.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond meter_Daily_Summary.errors, view:'edit'
            return
        }

        meter_Daily_Summary.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'meter_Daily_Summary.label', default: 'Meter_Daily_Summary'), meter_Daily_Summary.id])
                redirect meter_Daily_Summary
            }
            '*'{ respond meter_Daily_Summary, [status: OK] }
        }
    }

    @Transactional
    def delete(Meter_Daily_Summary meter_Daily_Summary) {

        if (meter_Daily_Summary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        meter_Daily_Summary.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'meter_Daily_Summary.label', default: 'Meter_Daily_Summary'), meter_Daily_Summary.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'meter_Daily_Summary.label', default: 'Meter_Daily_Summary'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
