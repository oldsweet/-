import axios from "./config";

/**
 * 上传文件
 * @param file
 * @param intro
 * @param filename
 */
async function submitFile(file, intro, filename, root = "/") {
  let formData = new FormData()
  formData.append("file", file)
  formData.append("intro", intro)
  formData.append("filename", filename)
  formData.append("token", localStorage.getItem("token"))
  let res = await axios.post("file/upload_file", formData, {
    headers: {'Content-Type': 'multipart/form-data'}
  })
  res = res.data
  return res
}

async function listFile() {
  let params = new URLSearchParams()
  params.set("token", localStorage.getItem("token"))
  let res = await axios.post("/file/all_file", params)
  res = res.data
  return res
}

async function delFile(fileId) {
  let params = new URLSearchParams()
  params.set("token", localStorage.getItem("token"));
  params.set("fileId",fileId);
  let res = await axios.post("/file/del_file", params)
  res = res.data
  return res
}


function getFileIcon(filename) {
  let suffix = filename.split(".")
  suffix = suffix[suffix.length - 1]
  return "..\\..\\..\\src\\assets\\file_icons\\" + suffix + ".png"
}

export {submitFile, listFile, getFileIcon,delFile}
