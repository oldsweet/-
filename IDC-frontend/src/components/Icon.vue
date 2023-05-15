<template>
  <div class="icon-container">
    <img src="@/assets/file_icons/tga.png" v-if="type === 'tga'">
    <img src="@/assets/file_icons/aac.png" v-else-if="type === 'aac'">
    <img src="@/assets/file_icons/ai.png" v-else-if="type === 'ai'">
    <img src="@/assets/file_icons/aiff.png" v-else-if="type === 'aiff'">
    <img src="@/assets/file_icons/avi.png" v-else-if="type === 'avi'">
    <img src="@/assets/file_icons/bmp.png" v-else-if="type === 'bmp'">
    <img src="@/assets/file_icons/c.png" v-else-if="type === 'c'">
    <img src="@/assets/file_icons/cpp.png" v-else-if="type === 'cpp'">
    <img src="@/assets/file_icons/css.png" v-else-if="type === 'css'">
    <img src="@/assets/file_icons/csv.png" v-else-if="type === 'csv'">
    <img src="@/assets/file_icons/dat.png" v-else-if="type === 'dat'">
    <img src="@/assets/file_icons/dmg.png" v-else-if="type === 'dmg'">
    <img src="@/assets/file_icons/doc.png" v-else-if="type === 'doc' ||type === 'docx'  ">
    <img src="@/assets/file_icons/dotx.png" v-else-if="type === 'dotx'">
    <img src="@/assets/file_icons/dwg.png" v-else-if="type === 'dwg'">
    <img src="@/assets/file_icons/dxf.png" v-else-if="type === 'dxf'">
    <img src="@/assets/file_icons/eps.png" v-else-if="type === 'eps'">
    <img src="@/assets/file_icons/exe.png" v-else-if="type === 'exe'">
    <img src="@/assets/file_icons/flv.png" v-else-if="type === 'flv'">
    <img src="@/assets/file_icons/gif.png" v-else-if="type === 'gif'">
    <img src="@/assets/file_icons/dwg.png" v-else-if="type === 'dwg'">
    <img src="@/assets/file_icons/h.png" v-else-if="type === 'h'">
    <img src="@/assets/file_icons/hpp.png" v-else-if="type === 'hpp'">
    <img src="@/assets/file_icons/html.png" v-else-if="type === 'html'">
    <img src="@/assets/file_icons/ics.png" v-else-if="type === 'ics'">
    <img src="@/assets/file_icons/iso.png" v-else-if="type === 'iso'">
    <img src="@/assets/file_icons/java.png" v-else-if="type === 'java'">
    <img src="@/assets/file_icons/jpg.png" v-else-if="type === 'jpg'">
    <img src="@/assets/file_icons/key.png" v-else-if="type === 'key'">
    <img src="@/assets/file_icons/less.png" v-else-if="type === 'less'">
    <img src="@/assets/file_icons/mid.png" v-else-if="type === 'mid'">
    <img src="@/assets/file_icons/mp3.png" v-else-if="type === 'mp3'">
    <img src="@/assets/file_icons/mp4.png" v-else-if="type === 'mp4'">
    <img src="@/assets/file_icons/mpg.png" v-else-if="type === 'mpg'">
    <img src="@/assets/file_icons/odf.png" v-else-if="type === 'odf'">
    <img src="@/assets/file_icons/ods.png" v-else-if="type === 'ods'">
    <img src="@/assets/file_icons/odt.png" v-else-if="type === 'odt'">
    <img src="@/assets/file_icons/otp.png" v-else-if="type === 'otp'">
    <img src="@/assets/file_icons/ots.png" v-else-if="type === 'ots'">
    <img src="@/assets/file_icons/ott.png" v-else-if="type === 'ott'">
    <img src="@/assets/file_icons/pdf.png" v-else-if="type === 'pdf'">
    <img src="@/assets/file_icons/php.png" v-else-if="type === 'php'">
    <img src="@/assets/file_icons/png.png" v-else-if="type === 'png'">
    <img src="@/assets/file_icons/otp.png" v-else-if="type === 'otp'">
    <img src="@/assets/file_icons/ppt.png" v-else-if="type === 'ppt'">
    <img src="@/assets/file_icons/psd.png" v-else-if="type === 'psd'">
    <img src="@/assets/file_icons/py.png" v-else-if="type === 'py'">
    <img src="@/assets/file_icons/qt.png" v-else-if="type === 'qt'">
    <img src="@/assets/file_icons/rar.png" v-else-if="type === 'rar'">
    <img src="@/assets/file_icons/rb.png" v-else-if="type === 'rb'">
    <img src="@/assets/file_icons/rtf.png" v-else-if="type === 'rtf'">
    <img src="@/assets/file_icons/sass.png" v-else-if="type === 'sass'">
    <img src="@/assets/file_icons/scss.png" v-else-if="type === 'scss'">
    <img src="@/assets/file_icons/sql.png" v-else-if="type === 'sql'">
    <img src="@/assets/file_icons/tga.png" v-else-if="type === 'tga'">
    <img src="@/assets/file_icons/tgz.png" v-else-if="type === 'tgz'">
    <img src="@/assets/file_icons/tiff.png" v-else-if="type === 'tiff'">
    <img src="@/assets/file_icons/txt.png" v-else-if="type === 'txt'">
    <img src="@/assets/file_icons/wav.png" v-else-if="type === 'wav'">
    <img src="@/assets/file_icons/xls.png" v-else-if="type === 'xls'">
    <img src="@/assets/file_icons/xlsx.png" v-else-if="type === 'xlsx'">
    <img src="@/assets/file_icons/xml.png" v-else-if="type === 'xml'">
    <img src="@/assets/file_icons/yml.png" v-else-if="type === 'yml' || type === 'yaml'">
    <img src="@/assets/file_icons/zip.png" v-else-if="type === 'zip'">
    <img src="@/assets/file_icons/_blank.png" v-else>
  </div>
</template>

<script>
export default {
  name: "Icon",
  props: {
    type: ""
  }
}
</script>

<style scoped>
.icon-container {
  height: 40px;
  width: 40px;
}

img {
  height: 40px;
  width: 40px;
}
</style>
