package com.ernest

import grails.rest.*

@Resource(uri='/metercharts')
class MeterCharts {

  String col
  static hasMany = [data:Data]

  static constraints = {
    
  }
}