<template>
  <div class="container">
    <div class="top-bar">
      <el-input
        v-model="search"
        placeholder="Search Files"
        @input="handleSearch"
        clearable
        style="width: 200px;"
        size="small"
      />
      <input type="file" ref="uploadInput" style="display: none">
      <el-button icon="el-icon-plus" size="small" @click="clickUploadFile">上传文件</el-button>
    </div>
    <main>
      <!--      文件渲染表单-->
      <el-table
        :data="displayedFiles">
        <el-table-column
          prop="filename"
          label="文件名"
        >
          <template slot-scope="scope">
            <div class="file">
              <div class="icon">
                <icon :type="fileType(scope.row.filename)"></icon>
              </div>
              {{ scope.row.filename }}
            </div>
          </template>
        </el-table-column>

        <el-table-column
          prop="createTime"
          label="上传时间"
          width="400">
          <template slot-scope="scope">
            <div class="time">
              {{ handlerTime(scope.row.createTime) }}
            </div>
          </template>
        </el-table-column>

        <el-table-column
          prop="intro"
          label="备注"
        >
          <template slot-scope="scope">
            <div class="intro">
              <div v-if="scope.row.intro !== 'null'">
                {{ scope.row.intro }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column>
          <template slot-scope="scope">
            <i class="el-icon-delete operation-icon" @click.stop="deleteFile(scope.row.id)"></i>
            <i class="el-icon-download operation-icon" @click.stop="downloadFile(scope.row.url)"></i>
            <i class="el-icon-more operation-icon" @click.stop="fileMore()"></i>
          </template>
        </el-table-column>

      </el-table>

      <el-pagination
        background
        :page-size="pageSize"
        :total="total"
        layout="prev, pager, next"
        @current-change="handlePageChange"
      />
    </main>
    <el-tag type="info" style="border: none">文件数量: {{ total }}</el-tag>
    <!--    文件上传对话框 -->
    <el-dialog
      title="文件上传"
      :visible.sync="filePanelVis"
      width="30%">
      <div v-if="uploadFile">
        <div style="display: flex;align-items: center;padding-bottom: 10px">
          <div class="icon">
            <icon :type="fileType(uploadFile.name)"></icon>
          </div>
          {{ uploadFile.name }}
        </div>
        <el-progress :percentage="percent" color="#409eff"></el-progress>
        <div style="display: flex;flex-direction: column;gap: 10px;margin-top: 10px">
          <div>文件大小: {{ uploadFile.size / 1024 }} KB</div>
          <div>文件类型: {{ fileType(uploadFile.name) }}</div>
          <div style="display: flex;align-items: center;gap: 16px">
            <div>数据源:</div>
            <el-select size="small" placeholder="请选择存储源" v-model="storageSource">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="filePanelVis = false">取 消</el-button>
    <el-button type="primary" @click="submitFile">确 定 上 传</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import {userInfo} from "../../api/user";
import {delFile, listFile} from "../../api/file"
import Icon from "../../components/Icon";
import {dateFormat} from "../../utils/time_utils";
import axios from "../../api/config";

export default {
  components: {Icon},
  data() {
    return {

      options: [{
        value: 'aliyunoss',
        label: '阿里云'
      }, {
        value: 'tecentyunoss',
        label: '腾讯云'
      },],
      storageSource: "aliyunoss",
      state2: "",
      files: [],
      uploadFile: null,
      filePanelVis: false,
      pageSize: 6,
      currentPage: 1,
      publicPath: process.env.BASE_URL,
      search: "",
      percent: 0,
      total: 0
    }
  },
  async mounted() {
    await this.init()
  },
  computed: {
    displayedFiles() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      let disFiles = null
      if (this.search !== "") {
        disFiles = this.files
          .filter((file) =>
            file.filename.toLowerCase().includes(this.search.toLowerCase())
          )
          .slice(startIndex, endIndex);
        this.total = this.files
          .filter((file) =>
            file.filename.toLowerCase().includes(this.search.toLowerCase())
          ).length
      } else {
        disFiles = this.files.slice(startIndex, endIndex)
        this.total = this.files.length
      }
      return disFiles
    },
  },
  methods: {
    async init() {
      let res = await userInfo()
      let fileRes = await listFile()
      this.files = fileRes.data
      console.log(this.files)
    },
    fileType(filename) {
      let filename_words = filename.split(".")
      return filename_words[filename_words.length - 1]
    },
    handlerTime(time) {
      time = new Date(time)
      return dateFormat("YYYY-mm-dd HH:MM", time)
    },
    handleSearch() {
      this.currentPage = 1;
    },
    handlePageChange(newPage) {
      this.currentPage = newPage;
    },
    clickUploadFile() {
      this.$refs.uploadInput.click()
      let that = this
      this.percent = 0
      this.$refs.uploadInput.addEventListener("change", function () {
        that.uploadFile = that.$refs.uploadInput.files[0]
        that.filePanelVis = true
      })
    },
    /**
     * 上传文件到服务器
     */ async submitFile() {
      this.percent = 0;
      let formData = new FormData()
      formData.append("file", this.uploadFile)
      formData.append("intro", "")
      formData.append("filename", this.uploadFile.name)
      formData.append("token", localStorage.getItem("token"))
      formData.append("storageSource", this.storageSource)
      axios.post("/file/upload_file", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
        onUploadProgress: (progressEvent) => {
          if (progressEvent) {
            this.percent = (((progressEvent.loaded / progressEvent.total) * 100) | 0)
          }
        },
      })
        .then(async (res) => {
          if (res.data.code === 200) {
            this.$message.success(res.data.msg);
            this.filePanelVis = false
            this.percent = 100
            await this.init()
          } else {
            this.$message.error(res.data.msg);
            await this.init()
          }
        });
    },
    async deleteFile(fileId) {

      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        let res = await delFile(fileId)

        if (res.code === 200) {
          await this.init()
          this.$message.success("删除成功");
        } else {
          this.$message.error("删除错误");
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });


    },
    downloadFile(url) {
      window.open(url)
    },
    fileMore() {
      console.log("fileMore")
    }
  }
}
</script>

<style scoped>
.container {
  background-color: #F3F4F6;
  height: 100%;
}

.top-bar {
  padding: 10px;
  width: 100%;
  background-color: white;
  display: flex;
  align-items: center;
  gap: 10px;
}

.file {
  display: flex;
  align-items: center;
  gap: 10px;
}

main {
  margin: 10px;
  flex-direction: column;
  align-items: center;
  background-color: white;
  display: flex;
  gap: 20px;
  padding-bottom: 10px;
}


.el-table {
  width: 100%;
}

.icon {
  height: 40px;
  width: 40px;
}

.icon > img {
  height: 100%;
  width: 100%;
}

.operation-icon {
  background-color: #E9EBEC;
  border-radius: 4px;
  padding: 10px;
  -moz-user-select: none; /*火狐*/

  -webkit-user-select: none; /*webkit浏览器*/

  -ms-user-select: none; /*IE10*/

  -khtml-user-select: none; /*早期浏览器*/

  user-select: none;
}

.operation-icon:hover {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}
</style>
