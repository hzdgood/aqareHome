<template>
  <div>
    <div>客户阶段</div>
    <div class="stageButton">
      <button
        v-for="item in customStage"
        :key="item.id"
        :id="item.id"
        :name="item.field"
        @click="onclick(item)"
      >
        {{ item.value }}
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { SearchInfo } from "@/config/interFace";
import { table, field, user } from "@/config/config";
@Component({})
export default class Home extends Vue {
  tagInfo = table.tagInfo;
  customerInfo = table.customerInfo;
  ticket = localStorage.getItem("ticket");
  customStage: any[] = [];
  taglist: any[] = [];
  async mounted() {
    const data = {
      limit: 20,
      offset: 0,
      order_by: [{ field: field.tagId, sort: "asc" }],
      where: {
        and: [{ field: 2200000182035321, query: { in: [2300006457896616] } }],
      },
    };
    const result = await SearchInfo(this.ticket, this.tagInfo, data);
    this.taglist = result;
    for (let i = 0; i < result.length; i++) {
      let name = result[i].fields[0].values[0].title;
      let value = result[i].fields[3].values[0].value;
      let item_id = result[i].item_id;
      let ob = {
        id: item_id,
        name: name,
        value: value,
        field: field.custominto,
      };
      this.customStage.push(ob);
    }
    console.log(this.customStage);
    this.getCustomerTag();
  }
  async getCustomerTag() {
    let data = {
      where: {
        and: [
          {
            query: { or: [{ in: [user.userId] }] },
            query_option_mappings: [-1],
            field: field.userTable,
          },
        ],
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: field.userTable, sort: "desc" }],
    };
    const res = await SearchInfo(this.ticket, this.customerInfo, data);
    const fields = res[0].fields;
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id == field.customerStage) {
        const values = fields[i].values;
        for (let j = 0; j < values.length; j++) {
          let itemid = values[j].item_id;
          let dom: any = document.getElementById(itemid);
          dom.className = "select";
        }
      }
    }
  }
  onclick = (items: any) => {
    let id: any = items.id;
    let dom: any = document.getElementById(id);
    if (dom.className == "select") {
      dom.className = "";
    } else {
      dom.className = "select";
    }
  };
}
</script>
<style scoped>
.stageButton button {
  text-align: center;
  border: 0px solid #bbb;
  margin: 0px 5px 5px 5px;
  background-color: rgb(230, 227, 227);
  font-size: 10px;
  color: cornflowerblue;
  padding: 0px 5px 0px 5px;
  border-radius: 5px;
}
.select {
  background-color: aquamarine !important;
}
</style>>