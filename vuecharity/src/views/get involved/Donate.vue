<template>
  <div>
    <el-form ref="form" :model="payForm" label-width="100px" label-position="left">
      <el-form-item prop="price" label="Price" style="width: 400px">
        <el-input type="text" v-model="payForm.price" placeholder="10"></el-input>
      </el-form-item>
      <el-form-item prop="currency" label="Currency" style="width: 400px">
        <el-input type="text" v-model="payForm.currency" placeholder="GBP"></el-input>
      </el-form-item>
      <el-form-item prop="method" label="Pay Method" style="width: 400px;">
        <el-input type="text" v-model="payForm.method" placeholder="paypal"></el-input>
      </el-form-item>
      <el-form-item prop="intent" label="Intent" style="width: 400px">
        <el-input type="text" v-model="payForm.intent" placeholder="sale"></el-input>
      </el-form-item>
      <el-form-item prop="description" label="Pay Method" style="width: 400px">
        <el-input type="text" v-model="payForm.description" placeholder="Payment Description"></el-input>
      </el-form-item>
      <el-form-item style="width: 400px">
        <el-button type="primary" @click="checkout">CheckOut</el-button>
        <el-button>Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "Donate",
        data() {
          return {
            payForm: {
              price: '10',
              currency: 'GBP',
              method: 'paypal',
              intent: 'sale',
              description: 'Payment Description'
              // price: '',
              // currency: '',
              // method: '',
              // intent: '',
              // description: ''
            }
          }
        },
        methods: {
          checkout() {
            this.$confirm('Do you wanna continue to checkout?', 'Donate', {
              confirmButtonText: 'Continue',
              cancelButtonText: 'Cancel',
              type: 'info'
            }).then(() => {
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
          }
          // checkout() {
          //   this.postKeyValueRequest('/pay', this.payForm).then(resp => {
          //     if (resp) {
          //       alert("Successful!")
          //               // this.$router.replace('/home')
          //     } else {
          //       alert("Fail!")
          //     }
          //   })
          // }
        }
    }
</script>

<style scoped>
  body {
    font-family: Arial,serif;
    font-size: 17px;
    padding: 8px;
  }

  * {
    box-sizing: border-box;
  }

  .row {
    display: -ms-flexbox; /* IE10 */
    display: flex;
    -ms-flex-wrap: wrap; /* IE10 */
    flex-wrap: wrap;
    margin: 0 -16px;
    width: 500px;
  }

  .col-25 {
    -ms-flex: 25%; /* IE10 */
    flex: 25%;
  }

  .col-50 {
    -ms-flex: 50%; /* IE10 */
    flex: 50%;
  }

  .col-75 {
    -ms-flex: 75%; /* IE10 */
    flex: 75%;
  }

  .col-25,
  .col-50,
  .col-75 {
    padding: 0 16px;
  }

  .container {
    background-color: #f2f2f2;
    padding: 5px 20px 15px 20px;
    border: 1px solid lightgrey;
    border-radius: 3px;
  }

  input[type=text] {
    width: 100%;
    margin-bottom: 20px;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 3px;
  }

  label {
    margin-bottom: 10px;
    display: block;
  }

  .icon-container {
    margin-bottom: 20px;
    padding: 7px 0;
    font-size: 24px;
  }

  .btn {
    background-color: #4CAF50;
    color: white;
    padding: 12px;
    margin: 10px 0;
    border: none;
    width: 100%;
    border-radius: 3px;
    cursor: pointer;
    font-size: 17px;
  }

  .btn:hover {
    background-color: #45a049;
  }

  a {
    color: #2196F3;
  }

  hr {
    border: 1px solid lightgrey;
  }

  span.price {
    float: right;
    color: grey;
  }

  /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
  @media (max-width: 800px) {
    .row {
      flex-direction: column-reverse;
    }
    .col-25 {
      margin-bottom: 20px;
    }
  }

</style>
