export const dateFilter = (data: any, format: string) => {
    if(data === null) {
        return ""
    }
    const dt = new Date(data);
    const y = dt.getFullYear();
    const m = (dt.getMonth()+1).toString().padStart(2,"0");
    const d = dt.getDate().toString().padStart(2,"0");
    const h = dt.getHours().toString().padStart(2,"0");
    const mm = dt.getMinutes().toString().padStart(2,"0");
    const s = dt.getSeconds().toString().padStart(2,"0");
    if (format.toLocaleLowerCase() === "yyyy-mm-dd" ||
        format.toLocaleLowerCase() === "") {
        return `${y}-${m}-${d}`;
    } else if (format.toLocaleLowerCase() === "yyyy/mm/dd") {
        return `${y}/${m}/${d}`;
    } else if (format.toLocaleLowerCase() === "yyyy-mm-dd hh:mm:ss") {
        return `${y}-${m}-${d} ${h}:${mm}:${s}`;
    } else if (format.toLocaleLowerCase() === "yyyy/mm/dd hh:mm:ss") {
        return `${y}/${m}/${d} ${h}:${mm}:${s}`;
    } else {
        return `输入的时间格式有误`;
    }
}