$(document).ready(function() {
var editor = new $.fn.dataTable.Editor( {
 ajax: {
  modify: {
    type: 'POST',
    url:  '/modifyrow/',
}
},
 
table: "#table",
fields: [ {
        label: "IP:",
        name: "ip"
    }, {
        label: "FQDN:",
        name: "fqdn"
    }, {
        label: "Server Name:",
        name: "server_name"
    }, {
        label: "Server Owner:",
        name: "serverowner"
    }, {
        label: "Application Name:",
        name: "application_name"
    }, {
        label: "App support contact:",
        name: "app_support_conatct",
    }, {
        label: "console:",
        name: "console"
    },{
        label: "Machinr Type:",
        name: "machine_type"
    }, {
        label: "Site Type:",
        name: "site_type"
    }
]
} );
 
// Activate an inline edit on click of a table cell
$('#table').on( 'click', 'tbody td:not(:first-child)', function (e) {
editor.inline( this, {
    buttons: { label: '&gt;', fn: function () { this.submit(); } }
} );
} );
 
// var data = response['data'];
         
             $('#table').DataTable( {
                  dom: "Bfrtip",
 
                 "ajax": {
                      "url": "/state/",
                      "data": "data",
                 },
                  
                 columns: [
                        {
                         data: null,
                         defaultContent: '',
                        className: 'select-checkbox',
                         orderable: false
                     },
                          { data:'ip'},
                {data:'fdqn'},
                {data:'server_name'},
                {data: 'serverowner'},
                {data:'application_name'},
                {data:'app_support_conatct'},
                {data:'console'},
                {data:'machine_type'},
                {data:'site_type'},
 
                      ],
order: [ 1, 'asc' ],
select: {
    style:    'os',
    selector: 'td:first-child'
},
buttons: [
     
    { extend: "modify",   editor: editor }
    
]
} );
});