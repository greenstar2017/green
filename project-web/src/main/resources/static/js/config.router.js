'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .run(
    [          '$rootScope', '$state', '$stateParams',
      function ($rootScope,   $state,   $stateParams) {
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;        
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider',
      function (  $stateProvider,   $urlRouterProvider) {
          $urlRouterProvider
              .otherwise('/app/receiptMng');
          var provider = $stateProvider
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: 'tpl/app.html?v='+new Date().getTime()
              });
          provider.state('app.receiptMng', {
                  url: '/receiptMng',
                  templateUrl: 'pages/receiptMng.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/receiptMng.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.lenderMng', {
                  url: '/lenderMng',
                  templateUrl: 'pages/lenderMng.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/lenderMng.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.statisicMng', {
                  url: '/statisicMng',
                  templateUrl: 'pages/statisicMng.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/statisicMng.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.systemAccount', {
                  url: '/systemAccount',
                  templateUrl: 'pages/systemAccount.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/systemAccount.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.systemAccount_edit', {
                  url: '/systemAccount_edit',
                  templateUrl: 'pages/systemAccount_edit.html',
                  params:{'id':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'pages/js/systemAccount_edit.js?v='+new Date().getTime()
								]);
                               }
                           );
                       }]
                  }
              })
              
              
              
              
          provider.state('app.news', {
                  url: '/news',
                  templateUrl: 'tpl/icca/news.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/news.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.news_edit', {
                  url: '/news_edit',
                  templateUrl: 'tpl/icca/news_edit.html',
                  params:{'id':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'js/icca/news_edit.js?v='+new Date().getTime(),
									'vendor/libs/ajaxfileupload.js'
								]);
                               }
                           );
                       }]
                  }
              }).state('app.travelFund', {
                  url: '/travelFund',
                  templateUrl: 'tpl/icca/travelFund.html',
                  params:{'parentId':''},
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/travelFund.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.travelFund_edit', {
                  url: '/travelFund_edit',
                  templateUrl: 'tpl/icca/travelFund_edit.html',
                  params:{'id':'', 'parentId':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'js/icca/travelFund_edit.js?v='+new Date().getTime()
								]);
                               }
                           );
                       }]
                  }
              }).state('app.works', {
                  url: '/works',
                  templateUrl: 'tpl/icca/works.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/works.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.works_edit', {
                  url: '/works_edit',
                  templateUrl: 'tpl/icca/works_edit.html',
                  params:{'id':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'js/icca/works_edit.js?v='+new Date().getTime(),
		                              'vendor/libs/ajaxfileupload.js'
								]);
                               }
                           );
                       }]
                  }
              }).state('app.art', {
                  url: '/art',
                  templateUrl: 'tpl/icca/art.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/art.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.art_edit', {
                  url: '/art_edit',
                  templateUrl: 'tpl/icca/art_edit.html',
                  params:{'id':''},
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load('toaster').then(
                                   function(){
                                      return $ocLazyLoad.load([
										'js/icca/art_edit.js?v='+new Date().getTime(),
										'vendor/libs/ajaxfileupload.js'
									]);
                                   }
                               );
                           }]
                  }
              }).state('app.book', {
                  url: '/book',
                  templateUrl: 'tpl/icca/book.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/book.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.book_edit', {
                  url: '/book_edit',
                  templateUrl: 'tpl/icca/book_edit.html',
                  params:{'id':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'js/icca/book_edit.js?v='+new Date().getTime(),
		                              'vendor/libs/ajaxfileupload.js'
								]);
                               }
                           );
                       }]
                  }
              }).state('app.officeIntroduce', {
                  url: '/officeIntroduce',
                  templateUrl: 'tpl/icca/officeIntroduce.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/officeIntroduce.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.officeTeam', {
                  url: '/officeTeam',
                  templateUrl: 'tpl/icca/officeTeam.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/officeTeam.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.officeTeam_edit', {
                  url: '/officeTeam_edit',
                  templateUrl: 'tpl/icca/officeTeam_edit.html',
                  params:{'id':''},
                  resolve: {
                	  deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'js/icca/officeTeam_edit.js?v='+new Date().getTime(),
		                              'vendor/libs/ajaxfileupload.js'
								]);
                               }
                           );
                       }]
                  }
              }).state('app.officeMember', {
                  url: '/officeMember',
                  templateUrl: 'tpl/icca/officeMember.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/officeMember.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.officeMember_edit', {
                  url: '/officeMember_edit',
                  templateUrl: 'tpl/icca/officeMember_edit.html',
                  params:{'id':''},
                  resolve: {
                	  deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'js/icca/officeMember_edit.js?v='+new Date().getTime(),
		                              'vendor/libs/ajaxfileupload.js'
								]);
                               }
                           );
                       }]
                  }
              }).state('app.job', {
                  url: '/job',
                  templateUrl: 'tpl/icca/job.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/job.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.job_edit', {
                  url: '/job_edit',
                  templateUrl: 'tpl/icca/job_edit.html',
                  params:{'id':''},
                  resolve: {
                	  deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'js/icca/job_edit.js?v='+new Date().getTime(),
		                              'vendor/libs/ajaxfileupload.js'
								]);
                               }
                           );
                       }]
                  }
              }).state('app.contact', {
                  url: '/contact',
                  templateUrl: 'tpl/icca/contact.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('js/icca/contact.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.contact_edit', {
                  url: '/contact_edit',
                  templateUrl: 'tpl/icca/contact_edit.html',
                  params:{'id':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'js/icca/contact_edit.js?v='+new Date().getTime(),
		                              'vendor/libs/ajaxfileupload.js'
								]);
                               }
                           );
                       }]
                  }
              })
      }
    ]
  );