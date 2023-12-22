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

const objOpen: any = {
    'C2电机': [3],
    'C3电机': [12],
    'B1电机': [2],
    'L2电机': [10],
    'Zigbee电机': [1],
    '梦幻帘': [7],
    '手动轨': [8],
    '直轨': [1],
    'L型左直角': [2], 
    'L型右直角': [4], 
    '异型轨': [3], 
    '超静音': [5],    
    '嵌入式': [6],
    '靠窗': [1],
    '居中': [2],
    '靠屋': [3],
    '单开': [1],
    '双开': [2],
    '左': [1],
    '右': [2],
    '木工板': [1],
    '混凝土': [2],
    '金属板': [3],
    '石膏板': [4],
    '标准扣减3-4cm': [2],
    '自定义扣减': [3],
    '不扣减(成品尺寸)': [2],
    '顶装': [1],
    '侧装': [2],
}

const objRoller: any = {
    '外顶装': [1],
    '侧装': [2],
    '内顶装': [3],
    '左': [1],
    '右': [2],
    '无电源': [3],
    '标准扣减1cm': [2],
    '尺寸已减': [1],
    '木板': [1],
    '混凝土': [2],
    '金属板': [3],
    '石膏板': [4],
    '其他': [5],
    '需要': [1],
    '不需要': [2]
}

export const getRoller = (name: string) => {
    for(const key in objRoller){
        if(key === name) {
            return objRoller[key]
        }
    }
}


export const getValue = (name: string) => {
    for(const key in objOpen){
        if(key === name) {
            return objOpen[key]
        }
    }
}