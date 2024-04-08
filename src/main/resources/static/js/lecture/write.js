
var tag = {};
var counter = 0;

function addTag(value){
    tag[counter] = value;
    counter++;
}

$("#tagRegister").on("keyup", function(e){
    var self = $(this);
    console.log("keypress");

    if(e.keyCode == 32){
        var tagValue = self.val().replace(/(\s*)/g, "");
        console.log(tagValue);

        if(tagValue !== ""){
            var result = Object.values(tag).filter(function(word){
                return word === tagValue;
            })

            console.log(result);

            if(result.length == 0){
                $("#tagList").append('<a class="tag">'+tagValue+'<span class="del-btn" idx="'+counter+'"><i class="fa-solid fa-x"></i></span></a>');
                $("#previewTagList").append('<a class="tag" id="prevTag'+counter+'">'+tagValue+'</a>');
                $("#writeLectureForm").append('<input type="hidden" value="'+tagValue+'" id="tag'+counter+'" name="tagContents">');
                addTag(tagValue);
                self.val("");
            }else{
                alert("태그값이 중복됩니다.");
            }
        }
        e.preventDefault();
    }
});

$(document).on("click", ".del-btn", function(e){
    var index = $(this).attr("idx");
    tag[index] = "";
    $(this).parent().remove();
    $('#prevTag'+index).remove();
    $('#tag'+index).remove();
});

function changeThumbnail(file){
    var reg = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;

    console.log(file.files[0].name);
    if(file.value == null){
        document.getElementById('prevImg').src = "/img/noimg.gif";
    } else if(file.value.match(reg)) {
        console.log("해당 파일은 이미지 파일입니다.");
        prevImg(file);
        $('#thumbnailModified').val(true);
    } else {
        console.log("해당 파일은 이미지 파일이 아닙니다.");
    }
}

function prevImg(file){
    if(file.files && file.files[0]){
        var reader = new FileReader();
        reader.onload = function(e){
             document.getElementById('prevImg').src = e.target.result;
        };
        reader.readAsDataURL(file.files[0]);
        $('#originalName').val(file.files[0].name);
    }
}

function cancelThumbnail(){
    $('#thumbnailInput').val('');
    $('#originalName').val('');
    $('#thumbnailModified').val(true);
    document.getElementById('prevImg').src = "/img/noimg.gif";
}


function keyupTitle(value){
    document.getElementById('lectureTitle').innerHTML = value;
}