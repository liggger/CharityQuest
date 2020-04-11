<template>
  <div>
    <el-table
            :data="projects"
            stripe
            size="small"
            style="width: 800px">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" class="table-expand">
            <el-form-item label="Project Name: ">
              <span>{{ props.row.projectName }}</span>
            </el-form-item>
            <el-form-item label="Create Date: ">
              <span>{{ props.row.createDate }}</span>
            </el-form-item>
            <el-form-item label="Expire Date: ">
              <span>{{ props.row.expireDate }}</span>
            </el-form-item>
            <el-form-item label="Target Money: ">
              <span>{{ props.row.targetMoney }}</span>
            </el-form-item>
            <el-form-item label="Total Money: ">
              <span>{{ props.row.totalMoney }}</span>
            </el-form-item>
            <el-form-item label="Description: ">
              <span>{{ props.row.description }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
              prop="projectId"
              label="ID"
              width="50">
      </el-table-column>
      <el-table-column
              prop="projectName"
              label="Project Name"
              width="120">
      </el-table-column>
      <el-table-column
              prop="createDate"
              label="Create Date"
              width="120">
      </el-table-column>
      <el-table-column
              prop="expireDate"
              label="Expire Date"
              width="120">
      </el-table-column>
      <el-table-column
              label="Status"
              width="120">
        <template slot-scope="scope">
          <el-tag size="small" type="info" v-if="scope.row.status==1">Finish</el-tag>
          <el-tag size="small" type="success" v-else>In progress</el-tag>
        </template>
      </el-table-column>
      <el-table-column
              label="Get Involved">
        <template slot-scope="scope">
          <el-button
                  size="mini"
                  type="primary"
                  @click="shareProject(scope.row)">Share</el-button>
          <el-button
                  size="mini"
                  type="danger"
                  @click="donateProject(scope.row)">Donate</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
            title="Share the project"
            :visible.sync="shareVisible"
            width="400px">
      <div>
        <div>
          <el-tag>Project Name</el-tag>
          {{project.projectName}}
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="shareVisible = false">Cancel</el-button>
        <el-button type="primary" @click="doShare(project.link)">Confirm</el-button>
      </span>
    </el-dialog>

    <el-dialog
            title="Donate the project"
            :visible.sync="donateVisible"
            width="400px">
      <div>
        <el-form class="payForm" ref="form" :model="payForm" label-width="130px" label-position="left">
          <el-form-item prop="price" label="Money">
            <el-input type="text" v-model="payForm.price" placeholder="10"></el-input>
          </el-form-item>
          <el-form-item prop="method" label="Pay Method">
            <el-input type="text" v-model="payForm.method" placeholder="paypal" disabled="true"></el-input>
          </el-form-item>
          <el-form-item prop="description" label="Pay Description">
            <el-input type="text" v-model="payForm.description" placeholder="Payment Description"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="donateVisible = false">Cancel</el-button>
        <el-button type="primary" @click="checkout">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'fundraise',
    data() {
      return {
        user: JSON.parse(window.sessionStorage.getItem("user")),
        projects: [],
        shareVisible: false,
        donateVisible: false,
        project: '',
        payForm: {
          price: '10',
          currency: 'GBP',
          method: 'paypal',
          intent: 'sale',
          description: 'Payment Description'
        },
        donationForm: {
          userId: '',
          projectId: '',
          money: '',
          date: ''
        }
      }
    },
    mounted() {
      this.initProjects();
    },
    methods: {
      initProjects() {
        this.getRequest("/projects").then(resp=>{
          if (resp) {
            this.projects = resp;
          }
        })
      },
      shareProject(data) {
        // Object.assign(this.linkToShow, data.link)
        this.project = data;
        this.shareVisible = true;
        // window.open(data.link)
      },
      doShare(link) {
        window.open(link)
        this.shareVisible = false;
      },
      donateProject(data) {
        // Object.assign(this.linkToShow, data.link)
        this.project = data
        this.donateVisible = true;
        // window.open(data.link)
      },
      doDonate(link) {
        this.donateVisible = false;
      },
      checkout() {
        this.$confirm('Do you wanna continue to checkout?', 'Donate', {
          confirmButtonText: 'Continue',
          cancelButtonText: 'Cancel',
          type: 'info'
        }).then(() => {
          this.payForm.intent = 'sale';
          this.payForm.currency = 'GBP';
          this.donationForm.userId = this.user.userId;
          this.donationForm.projectId = this.project.projectId;
          this.donationForm.money = this.payForm.price;
          this.donationForm.date = this.formatDate(new Date());
          this.postRequest('/donation', this.donationForm);
          this.postRequest('/pay', this.payForm).then(resp => {
            this.See(resp)
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'The transaction is cancelled!'
          });
        });
      },
      See (e) {
        window.location.href = e
      },
      formatDate(date) {
        let y = date.getFullYear();
        let m = date.getMonth() + 1;
        m = m < 10 ? '0' + m : m;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
      }
    }
  };
</script>

<style>
  .table-expand label {
    width: 120px;
    color: #99a9bf;
  }
  .table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .payForm .el-form-item {
    width: 300px;
  }
</style>
