<template>
    <div>
        <el-form label-position='left' ref="form" :model="form" label-width="80px" style="width: 300px">
            <el-form-item label="Subject: ">
                <el-input v-model="form.subject" size="small">
                </el-input>
            </el-form-item>
            <el-form-item label="Email: ">
                <el-input v-model="form.toMailAddress" size="small">
                </el-input>
            </el-form-item>
            <el-form-item label="Content: ">
                <el-input
                        type="textarea"
                        :rows="5"
                        placeholder="Please input the content..."
                        v-model="form.content">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="doSend">Send</el-button>
                <el-button >Clear</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "SendEmail",
        data() {
            return {
                form: {
                    subject: '',
                    toMailAddress: '',
                    content: ''
                }
            }
        },
        methods: {
            doSend() {
                this.postRequest('/email', this.form).then(resp => {
                    if (resp) {
                        this.initForm();
                    }
                })
            },
            initForm() {
                this.form.subject = '';
                this.form.toMailAddress = '';
                this.form.content = '';
            }
        }
    }
</script>

<style scoped>

</style>