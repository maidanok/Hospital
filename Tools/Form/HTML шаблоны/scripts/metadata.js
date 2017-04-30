define(function(){
    return {
        pageGroups: [{"id":"a1a2f95a-2384-db30-8bcf-22b827495da3","name":"Default group","pages":[{"id":"fd714227-133b-01f7-6eaf-bd4a3290b524","name":"Index"},{"id":"11aa3390-d7ab-ba87-0b5b-4199620a42eb","name":"Login"},{"id":"6af47441-027e-ef99-50ee-b3de4e4cb566","name":"Stacionar"},{"id":"29848234-1c3a-6009-39ac-ba97be2b1ef5","name":"AllPrescriptions"},{"id":"68bb5ed2-95a6-3692-496a-2af70d247051","name":"sicklist1"},{"id":"1f9e7bae-9a50-36c4-198f-d5e20183322e","name":"Directories1"},{"id":"d56debe0-63c2-dbb6-894f-c8342ce0efe5","name":"Directories2"},{"id":"5e7becd7-ec33-44b9-66d8-46403508b352","name":"Survey"},{"id":"622d8659-a380-4954-3681-fb4298394169","name":"Prescr"},{"id":"be51d75a-8308-4d3b-50ac-60d628c21f5f","name":"Directories3"},{"id":"20fe6171-56f9-f0c9-f81d-3630972470a4","name":"add Patients"},{"id":"26e57fdb-8557-5212-8c91-f622494f71e2","name":"Staff"},{"id":"9d041091-e780-1957-47c5-cd28d45db2c8","name":"Diagnose"},{"id":"24511aac-327d-153b-bede-82d8d176dfcb","name":"sicklist2"},{"id":"007a4164-85ea-ba96-9ab4-a2c76a8192aa","name":"sicklist3"}]}],
        downloadLink: "//services.ninjamock.com/html/htmlExport/download?shareCode=GR6CZ&projectName=Untitled project",
        startupPageId: 0,

        forEachPage: function(func, thisArg){
        	for (var i = 0, l = this.pageGroups.length; i < l; ++i){
                var group = this.pageGroups[i];
                for (var j = 0, k = group.pages.length; j < k; ++j){
                    var page = group.pages[j];
                    if (func.call(thisArg, page) === false){
                    	return;
                    }
                }
            }
        },
        findPageById: function(pageId){
        	var result;
        	this.forEachPage(function(page){
        		if (page.id === pageId){
        			result = page;
        			return false;
        		}
        	});
        	return result;
        }
    }
});
