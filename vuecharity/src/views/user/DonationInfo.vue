<template>
    <div>
        <el-table
                :data="donations"
                stripe
                style="width: 1000px">
            <el-table-column
                    prop="donationId"
                    label="Donation Id"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="projectId"
                    label="Project Id"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="money"
                    label="Money"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="Date">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "DonationInfo",
        data() {
            return {
                user: JSON.parse(window.sessionStorage.getItem("user")),
                donations: []
            }
        },
        mounted() {
            this.initDonations();
        },
        methods: {
            initDonations() {
                this.getRequest("/donation?userId=" + this.user.userId).then(resp=>{
                    if (resp) {
                        this.donations = resp;
                    }
                })
            }
        }
    }
</script>

<style>

</style>