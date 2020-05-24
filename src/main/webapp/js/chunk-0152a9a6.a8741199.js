(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0152a9a6"],{"0798":function(t,e,i){"use strict";i("a57f");var r=i("9d26"),s=i("b64a"),n=i("98a1"),o=i("2b0e"),a=o["a"].extend({name:"transitionable",props:{mode:String,origin:String,transition:String}}),c=i("58df");e["a"]=Object(c["a"])(s["a"],n["a"],a).extend({name:"v-alert",props:{dismissible:Boolean,icon:String,outline:Boolean,type:{type:String,validator:function(t){return["info","error","success","warning"].includes(t)}}},computed:{computedColor:function(){return this.type&&!this.color?this.type:this.color||"error"},computedIcon:function(){if(this.icon||!this.type)return this.icon;switch(this.type){case"info":return"$vuetify.icons.info";case"error":return"$vuetify.icons.error";case"success":return"$vuetify.icons.success";case"warning":return"$vuetify.icons.warning"}}},methods:{genIcon:function(){return this.computedIcon?this.$createElement(r["a"],{class:"v-alert__icon"},this.computedIcon):null},genDismissible:function(){var t=this;return this.dismissible?this.$createElement("a",{class:"v-alert__dismissible",on:{click:function(){t.isActive=!1}}},[this.$createElement(r["a"],{props:{right:!0}},"$vuetify.icons.cancel")]):null}},render:function(t){var e=[this.genIcon(),t("div",this.$slots.default),this.genDismissible()],i=this.outline?this.setTextColor:this.setBackgroundColor,r=t("div",i(this.computedColor,{staticClass:"v-alert",class:{"v-alert--outline":this.outline},directives:[{name:"show",value:this.isActive}],on:this.$listeners}),e);return this.transition?t("transition",{props:{name:this.transition,origin:this.origin,mode:this.mode}},[r]):r}})},"8c68":function(t,e,i){"use strict";i.r(e);var r=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("v-container",{attrs:{"fill-height":""}},[i("v-layout",{attrs:{"justify-center":"","fill-height":"","align-center":""}},[i("v-flex",{attrs:{xs6:""}},[i("v-card",[i("v-card-title",[i("v-flex",[i("h3",{staticClass:"display-1 font-weight-thin text-lg-center"},[t._v("Remove "+t._s("user"==t.$route.query.of?"User":"Party"))])])],1),i("v-form",[i("v-container",[i("v-alert",{staticClass:"mb-3",attrs:{value:t.showAlert,transition:"scale-transition",type:"error"}},[t._v("Invalid user")]),i("v-text-field",{attrs:{label:"user"==t.$route.query.of?"User ID":"PARTY ID",outline:""},model:{value:t.id,callback:function(e){t.id=e},expression:"id"}}),i("v-layout",{attrs:{"justify-center":""}},[i("div",[i("v-btn",{staticClass:"my-0",attrs:{color:"primary",round:"",outline:""},on:{click:t.remove}},[t._v("Submit")])],1)])],1)],1)],1)],1)],1)],1)},s=[],n=i("cebc"),o=i("2f62"),a={data:function(){return{id:"",showAlert:!1,forUser:!1}},watch:{$route:function(t,e){"user"==t.query.of?(this.forUser=!0,console.log(this.forUser)):(this.forUser=!1,console.log(this.forUser))}},computed:Object(n["a"])({},Object(o["e"])(["user"])),methods:Object(n["a"])({remove:function(){var t=this;"user"==this.$route.query.of?this.deleteUser(this.id).then(function(e){e.data;var i=e.status;200==i&&t.$router.push({name:"create-party"})}).catch(function(t){}):this.removeParty(this.id).then(function(e){e.data;var i=e.status;200==i&&t.$router.push({name:"create-party"})})}},Object(o["b"])(["deleteUser","removeParty"]))},c=a,u=i("0c7c"),l=i("6544"),h=i.n(l),f=i("0798"),d=i("8336"),v=i("b0af"),m=i("12b2"),p=i("a523"),y=i("0e8f"),b=i("4bd4"),g=i("a722"),$=i("2677"),w=Object(u["a"])(c,r,s,!1,null,null,null);e["default"]=w.exports;h()(w,{VAlert:f["a"],VBtn:d["a"],VCard:v["a"],VCardTitle:m["a"],VContainer:p["a"],VFlex:y["a"],VForm:b["a"],VLayout:g["a"],VTextField:$["a"]})},a57f:function(t,e,i){}}]);
//# sourceMappingURL=chunk-0152a9a6.a8741199.js.map